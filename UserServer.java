
import java.net.*;
import java.io.*;

/**
 * This class creates a server for the User to continuously recieve
 * messages from the ServiceProvider
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class UserServer extends Thread {

  private ServerSocket serverSocket = null;

  public UserServer(int portNum) {

    //Try to establish a connection
    try {
      serverSocket = new ServerSocket(portNum);
      System.out.println("User server started...");
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
        System.out.println("Recieved message: " + message.getMessageType());
      }
    } catch (IOException i) {
      System.out.println(i);
    } finally {
      try { socket.close(); } catch (IOException i) {}
    }

  }
}
