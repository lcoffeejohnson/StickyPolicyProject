
import java.net.*;
import java.io.*;

public class Client {

  String address = null;
  int portNum = 0;

  public Client(String address, int portNum) {
    this.address = address;
    this. portNum = portNum;
  }

  public boolean sendMessage(Message message) {
      try {
        Socket socket = new Socket(address, portNum);
        System.out.println("Client is connected to server");
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

  public static void main(String[] args) {
  
    //Create new Client
    Client client = new Client("localhost", 50001);

    //User user = new User(new Policy(true, true));

    System.out.println("Set up new client");

    //Send Message
    client.sendMessage(new Message(MessageType.NOTIFY));
    System.out.println("Message sent!");
    client.sendMessage(new Message(MessageType.ACKNOWLEDGE));
    System.out.println("Second message sent!");
    
    //Close out client
   /* try {
      client.getSocket().close();
    }
    catch (IOException i) {
      System.out.println(i);
    }*/
  }
   
}
