
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

 
  public UserData copyData(UserData data) {
    return new UserData(new StickyHeader(data.stickyHeader), data.hash);
  }

  public void storeData(UserData data, int copies) {
    database.add(data);
    for (int i = 0; i < copies; i++) {
      database.add(copyData(data));
    }
  } 

  public int deleteData(int delHash) {
    int foundData = 0;
    int databaseSize = database.size();
    
    for (int i = 0; i < databaseSize; i++) {
      if (database.get(i).hash == delHash) {
        database.get(i).valid = false;
        foundData++;
      }
    }

    return foundData; //Number deleted
  }

  public StickyHeader interpretMessage(Message msg) {
    MessageType msgType = msg.getMessageType();    
    StickyHeader msgSH = msg.getStickyHeader();
    int msgHash = msg.getHash();  

    switch (msgType) {
    case UPLOAD:
      if (msgSH != null && msgHash != 0) {
        storeData(new UserData(msgSH, msgHash), 2); //store data and 2 copies
      }
      else System.out.println("Message upload failed");
      break;
    case DELETE:
      System.out.println("Delete request recieved");
      deleteData(msgHash);
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

  public String toString() {
    String str = "[ ";
    int databaseSize = database.size();
    for (int i = 0; i < databaseSize; i++) {
      str += database.get(i).ts + ", ";
      str += database.get(i).stickyHeader + ", ";
      str += database.get(i).hash + ", " + database.get(i).valid;
      if (i < databaseSize - 1) str += "\n  ";
      else str += " ]";
    }
    return str;
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
  System.out.println("Database:\n" + sp);
  }
}
