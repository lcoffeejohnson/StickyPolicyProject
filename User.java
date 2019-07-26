
import java.util.ArrayList;
import java.io.*;

/**
 * This class simulates a User with a policy for how
 * the user wants his or her data handled 
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class User {

  ArrayList<Integer> hashVals = new ArrayList<Integer>();
  Policy policy = null;
  Client client = null;
  UserServer server = null;

  public User(Policy policy) {
    this.policy = policy;
  }

  /**
   * Creates and starts the User's server and client
   */
   public void startServers(int serverPort, String address, int clientPort) {
     if(clientPort != serverPort) { //Users's server and client cannot use the same port
       server = new UserServer(serverPort);
       client = new Client(address, clientPort);
       server.start();
     } else {
       System.out.print("Server and client port numbers cannot match. Client / server setup aborted.");
     }
   }

  /**
   * Uploads a piece of user data along with the user's
   * policy as a Message to the ServiceProvider
   */
  public boolean uploadData(String data) {
    if (client != null ) {
      StickyHeader stickyHeader = new StickyHeader(data, this.policy);
      int sHHash = stickyHeader.hashCode(); //Probably not a strong enough hash function
      hashVals.add(0, sHHash);
      Message toSend = new Message(MessageType.UPLOAD, stickyHeader, sHHash);
    
      return client.sendMessage(toSend);
    } 
    else return false;
  } 

  /**
   * Sends a delete request message along with the hash
   * of the data object the user wants deleted
   */
  public boolean deleteData(int hash) {
    if (client != null) {
      Message toSend = new Message(MessageType.DELETE, hash);
      return client.sendMessage(toSend);
    } 
    else return false;
  } 

  public static void main(String[] args) {
    Policy policy = new Policy(true, true);
    User user = new User(policy);
    User userB = new User(policy);

    //Start servers
    try {
      int serverPort = Integer.parseInt(args[0]);
      String cliAddress = args[1];
      int cliPort = Integer.parseInt(args[2]);
      user.startServers(serverPort, cliAddress, cliPort);
      userB.startServers(serverPort, cliAddress, cliPort);
    } catch (NumberFormatException n) {
      System.out.println(n);
    }

    //Upload user data
    boolean sent = user.uploadData("This is some data...");
    if(sent) System.out.println("Data sent successfully!");
    else System.out.println("There was an error in sending the data");

    boolean sentB = userB. uploadData("MORE DATA!");
    if(sentB) System.out.println("DataB sent successfully!");

    //Request sent data to be deleted

    user.deleteData(user.hashVals.get(0));
    userB.deleteData(userB.hashVals.get(0));
  }
}
