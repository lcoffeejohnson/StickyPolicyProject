
import java.net.*;
import java.io.*;

public class Server extends Thread {

  private ServerSocket serverSocket = null;
  private ServiceProvider serviceProvider = null;  

  public Server(int portNum, ServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;

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
        serviceProvider.interpretMessage(message);  
      } 
    } catch (IOException i) {
      System.out.println(i);
    } finally {
      try { socket.close(); } catch (IOException i) {}
    }

  }
}
