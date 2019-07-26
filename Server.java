
import java.net.*;
import java.io.*;

/**
 * This class creates a Server to continuously recieve messages 
 * from a Client 
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class Server extends Thread {

  private ServerSocket serverSocket = null;
  
  /**
   * Class contructor.
   *
   * @param  portNum  The port number for the server to connect to
   */
  public Server(int portNum) {
    //Try to establish a connection
    try {
      serverSocket = new ServerSocket(portNum);
      System.out.println("Server started...");
    } 
    catch(IOException i) {
      System.out.println(i);
    }
  }

  /**
   * Starts the server and continuously seeks clients looking to connect
   */  
  @Override
  public void run() {
   Socket socket = null;

   try {
      while (true) {
        socket = serverSocket.accept();
        ObjectInputStream messageFromClient = new ObjectInputStream(socket.getInputStream());
        Message message = null;
        //Object recieved should be of type Message
        try {
          message = (Message) messageFromClient.readObject();
        } catch (ClassNotFoundException c) {
          System.out.println(c);
        }
        System.out.println("Recieved message: " + message);
        //Give message to ServiceProvider to interpret
        ServiceProvider.interpretMessage(message);  
      } 
    } catch (IOException i) {
      System.out.println(i);
    } finally {
      try { socket.close(); } catch (IOException i) {}
    }

  }
}
