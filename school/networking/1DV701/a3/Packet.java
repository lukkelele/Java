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


class Packet {

  public static final int TFTPPORT = 4970;
	public static final int BUFSIZE = 516;
  public static final int DATA_SIZE = 512;
	public static final String READDIR = "/home/lukkelele/Code/java/school/networking/1DV701/a3/read/"; //custom address at your PC
	// OP codes
	public static final int OP_RRQ = 1;
	public static final int OP_WRQ = 2;
	public static final int OP_DAT = 3;
	public static final int OP_ACK = 4;
	public static final int OP_ERR = 5;

  // Offsets
  final int OP_OFFSET = 0;
  final int FILE_OFFSET = 2;
  final int DATA_OFFSET = 4;
  final int NUM_OFFSET = 2;
  final int MESSAGE_OFFSET = 4;

  InetSocketAddress clientAddress;
  InetAddress ip;
  byte[] buf;
  String file_name;

  byte[] pkg;
  int port;
  int pkg_length;
  
  public Packet(InetSocketAddress client) {
    this.clientAddress = client;
  }

  boolean send_data(DatagramSocket socket, String file_name) throws IOException {
    try {
      FileInputStream file_input;
      int filedata_length;
      pkg = new byte[BUFSIZE];
      File file = new File(READDIR, file_name);
      file_input = new FileInputStream(file);
      filedata_length = file_input.read(pkg, 4, DATA_SIZE);
      file_input.close();
      pkg[0] = (byte) 0; 
      pkg[1] = (byte) 0; 
      // block 
      pkg[2] = (byte) 0; 
      pkg[3] = (byte) 1; 
      pkg_length = filedata_length + 4;
      DatagramPacket datagram = new DatagramPacket(pkg, pkg_length, clientAddress);
      socket.send(datagram);
      show(datagram);
      System.out.println("Datagram sent!");
      return true;
    } catch (Exception e) {
      System.out.println("send_DATA_recieve_ACK ---> error found.. \n"+e);
      return false;
    }
  }


  boolean insert(int offset, String s, byte delimiter) {
    //s.getBytes(0, s.length(), pkg, i); 
    try {
      byte[] byte_s = s.getBytes(Charset.defaultCharset());
      int index = offset;
      for (byte b : byte_s) {
        System.out.println("byte b: "+b);
        pkg[index++] = b;    // Insert into right spot      
        System.out.println("index: "+index);
      } // insert 0 after last insertion
      pkg[index] = delimiter;
      return true;
    } catch (Exception e) { 
        System.out.println(e);
        return false;
    }
  }


  boolean insert_with_delimiter(int offset, String s, byte delimiter) {
    //s.getBytes(0, s.length(), pkg, i); 
    try {
      byte[] byte_s = s.getBytes(Charset.defaultCharset());
      int index = offset;
      for (byte b : byte_s) {
        System.out.println("byte b: "+b);
        pkg[index++] = b;    // Insert into right spot      
        System.out.println("index: "+index);
      } // insert 0 after last insertion
      System.out.println("index end: "+index);
      pkg[index] = delimiter;
      return true;
    } catch (Exception e) { 
        System.out.println(e);
        return false;
    }
  }




  void show(DatagramPacket datagram) {
      System.out.println("=== DATAGRAM ===\n- addr: "+datagram.getAddress()+"\n- data: "+datagram.getData()
          +"\n- port: "+datagram.getPort()+"\n- msg length: "+datagram.getLength()
          +"\n- ip: "+datagram.getAddress().toString()
          +"\n- file: "+datagram.getData().toString()+"\n================\n");
  }

}
