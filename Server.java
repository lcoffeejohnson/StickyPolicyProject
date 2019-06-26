
import java.net.*;
import java.io.*;

public class Server {

  private ServerSocket serverSocket = null;

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


 /* public Message recieveMessage() throws IOException, ClassNotFoundException {
    ObjectInputStream messageFromClient = new ObjectInputStream(socket.getInputStream());
    Message message = (Message) messageFromClient.readObject();
    return message;
  }*/ 

  public static void main(String[] args) {
  
    //Create new Server object
    Server server = new Server(50001);
    Socket socket = null;   

    try {
      while (true) {
        socket = server.serverSocket.accept();
        ObjectInputStream messageFromClient = new ObjectInputStream(socket.getInputStream());
        Message message = null;
        try {
          message = (Message) messageFromClient.readObject();
        } catch (ClassNotFoundException c) {
          System.out.println(c);
        }
        System.out.println("Recieved message: " + message);  
      } 
    } catch (IOException i) {
      System.out.println(i);
    }
     
    //Try to close out socket
    try {
      socket.close();
    }
    catch (IOException i) {
      System.out.println(i);
    }
  } 

}
