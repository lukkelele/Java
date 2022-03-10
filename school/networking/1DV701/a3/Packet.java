import java.nio.ByteBuffer;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;


class Packet {

  final int MAX_PACKAGE_LENGTH = 516;
  final int MAX_DATA_LENGTH = 512;

  // Offsets
  final int OP_OFFSET = 0;
  final int FILE_OFFSET = 2;
  final int DATA_OFFSET = 4;
  final int NUM_OFFSET = 2;
  final int MESSAGE_OFFSET = 4;

  // Packet
  byte[] message;
  int message_length;

  
  public Packet() {
    message = new byte[MAX_PACKAGE_LENGTH];
    message_length = MAX_PACKAGE_LENGTH;
  }

  static Packet recieve(DatagramSocket socket) throws IOException {
    Packet in = new Packet();
     

  }

}
