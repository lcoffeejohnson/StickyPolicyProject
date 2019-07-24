
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
  Policy myPolicy = null;
  Client myClient = null;
  UserServer myServer = null;

  public User(Policy myPolicy) {
    this.myPolicy = myPolicy;
    myClient = new Client("localhost", 50001);
    myServer = new UserServer(50002);
    myServer.start();
  }

  /**
   * Uploads a piece of user data along with the user's
   * policy as a Message to the ServiceProvider
   */
  public boolean uploadData(String data) {
    StickyHeader stickyHeader = new StickyHeader(data, this.myPolicy);
    int sHHash = stickyHeader.hashCode(); //Probably not a strong enough hash function
    hashVals.add(0, sHHash);
    Message toSend = new Message(MessageType.UPLOAD, stickyHeader, sHHash);
    
    return myClient.sendMessage(toSend);
  } 

 /**
  * Sends a delete request message along with the hash
  * of the data object the user wants deleted
  */
 public boolean deleteData(int hash) {
   Message toSend = new Message(MessageType.DELETE, hash);
   return myClient.sendMessage(toSend);
 } 

  public static void main(String[] args) {
    Policy policy = new Policy(true, true);
    User user = new User(policy);
    //Upload user data
    boolean sent = user.uploadData("This is some data...");
    if(sent) System.out.println("Data sent successfully!");
    else System.out.println("There was an error in sending the data");
    //Request sent data to be delted
    user.deleteData(user.hashVals.get(0));
  }
}
