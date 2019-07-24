
import java.net.*;
import java.io.*;

public class Client {

  private String address = null;
  private int portNum = 0;

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
}
