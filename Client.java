
import java.net.*;
import java.io.*;

public class Client {

  private Socket socket = null;

  public Client(String address, int portNum) {

    //Try to establish connection with server 
    try {
      socket = new Socket(address, portNum);
      System.out.println("Client is connected to server");
    } 
    catch(UnknownHostException u) {
      System.out.println(u);
    } 
    catch(IOException i) {
      System.out.println(i);
    }

  }

  public Socket getSocket() {
    return socket;
  }

  public void sendMessage(Socket socket, Message message) throws IOException {
    ObjectOutputStream messageToServer = new ObjectOutputStream(socket.getOutputStream());
    messageToServer.writeObject(message);
    messageToServer.flush();
    messageToServer.close();
  }

  public static void main(String[] args) {
  
    //Create new Client
    Client client = new Client("localhost", 50001);

    System.out.println("Set up new client");

    //Send Message
    try {
      client.sendMessage(client.getSocket(), new Message(MessageType.NOTIFY));
      System.out.println("Message sent!");
    }
    catch(IOException i) {
      System.out.println(i);
    }
    
    //Close out client
    try {
      client.getSocket().close();
    }
    catch (IOException i) {
      System.out.println(i);
    }
  }
   
}
