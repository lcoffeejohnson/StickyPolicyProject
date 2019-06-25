
import java.util.ArrayList;
import java.io.*;

public class User {

  ArrayList<Integer> hashVals = new ArrayList<Integer>();
  Policy myPolicy = null;
  Client myClient = null;

  public User(Policy myPolicy) {
    this.myPolicy = myPolicy;
    myClient = new Client("localhost", 50001);
  }

  public boolean uploadData(String data) {
    StickyHeader stickyHeader = new StickyHeader(data, this.myPolicy);
    int sHHash = stickyHeader.hashCode(); //Probably not a strong enough hash function
    hashVals.add(0, sHHash);
    Message toSend = new Message(MessageType.UPLOAD, stickyHeader, sHHash);
    
    //Try to send the message
    try {
      myClient.sendMessage(myClient.getSocket(), toSend);
      return true;
    }
    //If unsuccessful, return false
    catch(IOException i) {
      System.out.println(i);
      return false;
    }

  } 

 public boolean deleteData(int hash) {
   Message toSend = new Message(MessageType.DELETE, hash);
   try {
     myClient.sendMessage(myClient.getSocket(), toSend);
     return true;
   }
   catch(IOException i) {
     System.out.println(i);
     return false;
   }
 } 

  public static void main(String[] args) {
    Policy policy = new Policy(true, true);
    User user = new User(policy);
    boolean sent = user.uploadData("This is some data...");
    if(sent) System.out.println("Data sent successfully!");
    else System.out.println("There was an error in sending the data");
    user.deleteData(user.hashVals.get(0));
  }
}
