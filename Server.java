
import java.net.*;
import java.io.*;

/**
 * This class creates a Server to continueoulsy recieve messages 
 * from a Client 
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class Server extends Thread {

  private ServerSocket serverSocket = null;
  //private ServiceProvider serviceProvider = null;  

  public Server(int portNum) {
    //this.serviceProvider = serviceProvider;

    //Try to establish a connection
    try {
      serverSocket = new ServerSocket(portNum);
      System.out.println("Server started...");
    } 
    catch(IOException i) {
      System.out.println(i);
    }
  }

  @Override
  public void run() {
   Socket socket = null;
 
   try {
      while (true) {
        socket = serverSocket.accept();
        ObjectInputStream messageFromClient = new ObjectInputStream(socket.getInputStream());
        Message message = null;
        try {
          message = (Message) messageFromClient.readObject();
        } catch (ClassNotFoundException c) {
          System.out.println(c);
        }
        System.out.println("Recieved message: " + message);
        ServiceProvider.interpretMessage(message);  
      } 
    } catch (IOException i) {
      System.out.println(i);
    } finally {
      try { socket.close(); } catch (IOException i) {}
    }

  }
}
