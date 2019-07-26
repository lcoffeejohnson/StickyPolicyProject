
import java.net.*;
import java.io.*;

/**
 * This class creates a Client to send messages to the Server
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class Client {

  private String address = null;
  private int portNum = 0;

  /**
   * Class constructor.
   *
   * @param  address  The IP address for the client to connect to
   * @param  portNum  The port number for the client to connect to 
   */
  public Client(String address, int portNum) {
    this.address = address;
    this. portNum = portNum;
  }

  /**
   * Sends a message of type Message to the ServiceProvider
   *
   * @param message  A Message object to be sent
   * @return         True if message was sent successfully, false otherwise
   */
  public boolean sendMessage(Message message) {
      try {
        Socket socket = new Socket(address, portNum);
        ObjectOutputStream messageToServer = new ObjectOutputStream(socket.getOutputStream());
        messageToServer.writeObject(message);
        messageToServer.flush();
        return true;
      } 
      catch(UnknownHostException u) {
        System.out.println(u);
        return false;
      } 
      catch(IOException i) {
        System.out.println(i);
        return false;
      }
    }
}
