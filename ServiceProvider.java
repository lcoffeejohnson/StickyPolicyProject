
import java.util.ArrayList;
import java.io.*;
import java.net.*;


/**
 * This class simulates a ServiceProvider with a database 
 * to store user data
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class ServiceProvider {

  static ArrayList<UserData> database = new ArrayList<UserData>();
  static Server server = null; 
  static Client client = null; 

  public ServiceProvider() {
  }

  /**
   * Returns a single copy of a UserData object
   * 
   * @param data A UserData object to copy
   * @return  A UserData object with the same sticky header and hash as the input 
   */
  public static UserData copyData(UserData data) {
    return new UserData(new StickyHeader(data.stickyHeader), data.hash);
  }

  /**
   * Creates and starts the ServiceProvider's server and client
   */
   public static void startServers(int serverPort, String address, int clientPort) {
     if(clientPort != serverPort) { //ServiceProvider's server and client cannot use same port
       server = new Server(serverPort);
       client = new Client(address, clientPort);
       server.start();
     } else {
       System.out.print("Server and client port numbers cannot match. Client / server setup aborted.");
     }
   }

  /**
   * Adds a UserData object to the database with specified number of copies
   */
  public static void storeData(UserData data, int copies) {
    database.add(data);
    for (int i = 0; i < copies; i++) {
      database.add(copyData(data));
    }
  } 

  /**
   * Deletes all UserData objects with the given hash
   */
  public static int deleteData(int delHash) {
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

  /**
   * If the data is present in the databse, 
   * returns the first UserData object that matches the
   * given hash and notifies client of access. Otherwise 
   * returns null.
   */
  public static StickyHeader accessData(int accessHash) {
    int databaseSize = database.size();

    for (int i = 0; i < databaseSize; i++) {
      UserData data = database.get(i);
      if (data.hash == accessHash && data.valid) {
        client.sendMessage(new Message(MessageType.NOTIFY));
        return data.stickyHeader;
      }
    }
    return null; //Data not found or not allowed to be accessed
  }

  /**
   * Depending on message type, handle data accordingly.
   */
  public static StickyHeader interpretMessage(Message msg) {
    MessageType msgType = msg.getMessageType();    
    StickyHeader msgSH = msg.getStickyHeader();
    int msgHash = msg.getHash();  

    switch (msgType) {
    //Upload data to database with 2 copies 
    case UPLOAD:
      if (msgSH != null && msgHash != 0) {
        storeData(new UserData(msgSH, msgHash), 2); //store data and 2 copies
        System.out.println("Upload message recieved.\nUpdated database:\n" + printDatabase());
        System.out.println("\nAccess attempt: " + accessData(msgHash)); //try to access new data
      }
      else System.out.println("Message upload failed");
      break;
    //Delete data and send acknowledgement to client
    case DELETE:
      deleteData(msgHash);
      System.out.println("Delete request recieved.\nUpdated database:\n" + printDatabase());
      client.sendMessage(new Message(MessageType.ACKNOWLEDGE));
      System.out.println("\nAccess attempt: " + accessData(msgHash)); //try to access deleted data
      break;
    //None of the following messages should be recieved by the ServiceProvider
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

  /**
   * Prints out the current contents of the database
   */
  public static String printDatabase() {
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
  
  /**
   * Main method for running ServiceProvider 
   * Takes server port number, client address and client port 
   * number as arguements
   * 
   */  
  public static void main(String[] args) {
    ServiceProvider sp = new ServiceProvider();
    try {
      int serverPort = Integer.parseInt(args[0]);
      String cliAddress = args[1];
      int cliPort = Integer.parseInt(args[2]);
      ServiceProvider.startServers(serverPort, cliAddress, cliPort);
    } catch (NumberFormatException n) {
      System.out.println(n);
    }
  }
}
