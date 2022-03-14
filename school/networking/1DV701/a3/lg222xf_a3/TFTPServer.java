
import java.nio.ByteBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.io.DataInputStream;
import java.io.DataOutputStream;



public class TFTPServer 
{
  public static final int TFTPPORT = 4970;
	public static final int BUFSIZE = 516;
  public static final int DATA_SIZE = 512;
	public static final String READDIR =  "/home/lukas/Code/java/school/networking/1DV701/a3/read/"; //custom address at your PC
	public static final String WRITEDIR = "/home/lukas/Code/java/school/networking/1DV701/a3/write/"; //custom address at your PC

	// OP codes
	public static final int OP_RRQ = 1;
	public static final int OP_WRQ = 2;
	public static final int OP_DAT = 3;
	public static final int OP_ACK = 4;
	public static final int OP_ERR = 5;

  byte[] buf;
  int port;
  InetSocketAddress clientAddress;
  String file_name;

  //             RRQ/WRQ   packet
  //
  //  2 bytes    string   1 byte   string  1 byte
  // |--------------------------------------------|
  // | opcode | filename |   0   |  Mode  |   0   |
  // |--------------------------------------------|


	public static void main(String[] args) {
		if (args.length > 0) 
		{
			System.err.printf("usage: java %s\n", TFTPServer.class.getCanonicalName());
			System.exit(1);
		}
		//Starting the server
		try 
		{
			TFTPServer server= new TFTPServer();
			server.start();
		}
		catch (SocketException e) 
			{e.printStackTrace();}
	}


	private void start() throws SocketException 
	{
		buf = new byte[BUFSIZE];
		// Create socket
		DatagramSocket socket= new DatagramSocket(null);
		// Create local bind point 
		SocketAddress localBindPoint= new InetSocketAddress(TFTPPORT);
		socket.bind(localBindPoint);

		System.out.printf("Listening at port %d for new requests\n", TFTPPORT);
		// Loop to handle client requests 
		while (true) {
			try {
          clientAddress = receiveFrom(socket, buf);
          System.out.println("clientAddress: "+clientAddress);
      } catch (IOException i) {
          System.out.println(i);
          break;
      }
			// If clientAddress is null, an error occurred in receiveFrom()
			if (clientAddress == null) break;

			final StringBuffer requestedFile = new StringBuffer();
			final int reqtype = ParseRQ(buf, requestedFile);

			new Thread() 
			{
				public void run() 
				{
					try 
					{
						DatagramSocket sendSocket= new DatagramSocket(0);
						// Connect to client
						sendSocket.connect(clientAddress);						
            System.out.println("Request: "+reqtype
                              +"\nHost: "+clientAddress.getAddress()
                              +"\nPort: "+clientAddress.getPort()); 
						// Read request
						if (reqtype == OP_RRQ) {      
              System.out.println("Incoming READ request...");
							HandleRQ(sendSocket, OP_RRQ);
						} else {   
              System.out.println("Incoming WRITE request...");
							HandleRQ(sendSocket, OP_WRQ);  
						}
						sendSocket.close();
					} catch (SocketException e) {
              e.printStackTrace(); 
          }
				}
			}.start();
		}
	}
	
	/**
	 * Reads the first block of data, i.e., the request for an action (read or write).
   *
	 * @param socket (socket to read from)
	 * @param buf (where to store the read data)
	 * @return socketAddress (the socket address of the client)
	 */
	private InetSocketAddress receiveFrom(DatagramSocket socket, byte[] buf) throws IOException {
    // Create datagram packet
    DatagramPacket in_packet = new DatagramPacket(buf, BUFSIZE);
		// Receive packet
    socket.receive(in_packet);	
		// Get client address and port from the packet
    port = in_packet.getPort();
    InetAddress inet_addr = in_packet.getAddress();
    InetSocketAddress inet_socket_addr = new InetSocketAddress(inet_addr, port);
    return inet_socket_addr;
	}

	/**
	 * Parses the request in buf to retrieve the type of request and requestedFile.
	 * 
	 * @param buf (received request)
	 * @param requestedFile (name of file to read/write)
	 * @return opcode (request type: RRQ or WRQ)
	 */ 
	private int ParseRQ(byte[] buf, StringBuffer requestedFile) {
    ByteBuffer container = ByteBuffer.wrap(buf);
    short opcode = container.getShort();   
    file_name = get_filename(2, (byte) 0, requestedFile); // Get filename starting with an offset of 2 bytes
    return opcode;
	}

	/**
	 * Handles RRQ and WRQ requests.
	 * 
	 * @param sendSocket (socket used to send/receive packets)
	 * @param requestedFile (name of file to read/write)
	 * @param opcode (RRQ or WRQ)
	 */
	private void HandleRQ(DatagramSocket sendSocket, int opcode) 
	{		
		if (opcode == OP_RRQ) {
            boolean result = send_DATA_receive_ACK(sendSocket);
            if (result == true) { 
                System.out.println("Datagram sent!");
            } else {
                System.out.println("Error sending datagram with opcode: "+opcode);
            }
        }
		else if (opcode == OP_WRQ) {
            boolean result = receive_DATA_send_ACK(sendSocket);
            if (result == true) { 
                System.out.println("Datagram sent!");
            } else {
                System.out.println("Error sending datagram with opcode: "+opcode);
            }
		} else {
			System.err.println("Invalid request. Sending an error packet.");
			send_ERR(sendSocket);
    }
  }

  /**
   * Send data in buffer byte array to the client.
   *
   * @param socket is the socket connected to the client address
   * @return true if the datagram is correctly sent and false otherwise
   */
  private boolean send_DATA_receive_ACK(DatagramSocket socket) {
    try {      
      FileInputStream file_input;         // FileInputStream to read data
      int filedata_length;                // the amount of bytes to be sent in datagram
      byte[] pkg = new byte[BUFSIZE];     // Create new byte array to hold the data to be included in the datagram
      File file = new File(READDIR, file_name);
      file_input = new FileInputStream(file);   
      filedata_length = file_input.read(pkg, 4, DATA_SIZE) + 4; // Add 4 for the opcode and block bytes
      file_input.close();
      // opcode bytes
      pkg[0] = 0;   
      pkg[1] = 3;   // 3 = 0b101
      // block bytes
      pkg[2] = 0;   
      pkg[3] = 1;   // block = 1 because the file sent is <512 bytes
      // display_bytes(pkg); // DEBUGGING
      DatagramPacket datagram = new DatagramPacket(pkg, filedata_length, clientAddress);
      socket.send(datagram); // Send the datagram  
      show(datagram);        // Display datagram properties in terminal
      return true;           // If the datagram is successfully sent, return true
    } catch (Exception e) {
      System.out.println(e);
      return false;          // If the datagram cant be sent, return false
    }
  }

	private boolean receive_DATA_send_ACK(DatagramSocket socket) { return true; }

  private void send_ERR(DatagramSocket socket) {  }

  /**
   * Display a datagram and its information in the terminal.
   *
   * @param datagram is the datagram to display information about
   */
  void show(DatagramPacket datagram) {
      System.out.println("\n=== DATAGRAM ===\n- addr: "+datagram.getAddress()
          +"\n- port: "+datagram.getPort()
          +"\n- package length: "+datagram.getLength()
          +"\n================\n");
  }

  /**
   * Iterates the byte buf array and adds characters to a stringbuffer until a
   * delimiter byte has been reached.
   *
   * @param i is the offset to start to index from
   * @param end is the byte to stop adding characters to s
   * @param s is the StringBuffer who holds the created string
   * @return StringBuffer s
   */
  String get_filename(int i, byte end, StringBuffer s) {
    while (buf[i] != end) {
      s.append((char) buf[i++]);
    } 
    return s.toString();
  }

  /**
   * Displays all bytes in a byte array.
   * Used for debug purposes with the packet headers.
   *
   * @param pkg is the byte array that is to be printed to the terminal.
   */
  void display_bytes(byte[] pkg) {
    System.out.print("\n");
    for (byte b : pkg) {
      System.out.print(b);
    }
    System.out.print("\n");
  }

}



