
import java.net.*;
import java.io.*;

public class Server {

  private Socket socket = null;
  private ServerSocket serverSocket = null;

  public Server(int portNum) {
  
    //Try to establish a connection
    try {
      serverSocket = new ServerSocket(portNum);
      System.out.println("Server started...");
      socket = serverSocket.accept();
      System.out.println("Connection with client successful!");

    } 
    catch(IOException i) {
      System.out.println(i);
    }
  }

  public Socket getSocket() {
    return socket;
  }

  public Message recieveMessage() throws IOException, ClassNotFoundException {
    ObjectInputStream messageFromClient = new ObjectInputStream(socket.getInputStream());
    Message message = (Message) messageFromClient.readObject();
    return message;
  } 

  public static void main(String[] args) {
  
    //Create new Server object
    Server server = new Server(50001);
    
    try {
      System.out.println("Tryng to recieve message....");
      System.out.println("Recieved message: " + server.recieveMessage());
    }
    catch (IOException i) {
      System.out.println(i);
    }
    catch (ClassNotFoundException c) {
      System.out.println(c);
    }
    
    //Try to close out socket
    try {
      server.getSocket().close();
    }
    catch (IOException i) {
      System.out.println(i);
    }
  } 

}
