
import java.util.ArrayList;
import java.io.*;
import java.sql.Timestamp;

public class ServiceProvider {

  Server myServer = null;
  ArrayList<UserData> database = new ArrayList<UserData>();

  public class UserData {
    Timestamp ts = null;
    StickyHeader stickyHeader = null;
    int hash = 0;
    boolean valid = false;
    
    public UserData(StickyHeader stickyHeader, int hash) {
      ts = new Timestamp(System.currentTimeMillis());
      this.stickyHeader = stickyHeader;
      this.hash = hash;
      valid = true;
    }
  }

  public ServiceProvider() {
    myServer = new Server(50001);
  }

  public StickyHeader interpretMessage(Message msg) {
    MessageType msgType = msg.getMessageType();    
    StickyHeader msgSH = msg.getStickyHeader();
  
    switch (msgType) {
    case UPLOAD:
      int msgHash = msg.getHash();
      if (msgSH != null && msgHash != 0) {
        database.add(new UserData(msgSH, msgHash));
        System.out.println("User data added to databse: " + msgSH.getData());
        System.out.println("User data hash: " + msgHash);
      }
      else System.out.println("Message upload failed");
      break;
    case DELETE:
      System.out.println("Delete request recieved");
      break;
    case NOTIFY:
      System.out.println("Notify message recieved");
      break;
    case ACKNOWLEDGE:
      System.out.println("Acknowledge message recieved");
      break;
    default:
      System.out.println("Something went wrong here...");
    }

    return msgSH; //Will be null if message was not an upload
  }
    
  public static void main(String[] args) {
    ServiceProvider sp = new ServiceProvider();
    try {
      Message msg = sp.myServer.recieveMessage();
      sp.interpretMessage(msg);
    }
    catch (IOException i) {
      System.out.println(i);
    }
    catch (ClassNotFoundException c) {
      System.out.println(c);
    }
  System.out.println("Database: " + sp.database);
  }
}
