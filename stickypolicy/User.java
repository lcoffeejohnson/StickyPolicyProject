package stickypolicy;

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

  /**
   * Class constructor for creating a User which requires a User Policy
   *
   * @param policy The Policy for how a User wants data handled
   */
  public User(Policy policy) {
    this.policy = policy;
  }

  /**
   * Creates and starts the User's server and client
   *
   * @param serverPort  The port number for the server to connect to
   * @param address     The IP address for the client to connect to 
   * @param clientPort  The port number for the client to connect to
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
   *
   * @param data  The data to be uploaded
   * @return      True if data was sent successfully, false otherwise
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
   * Sends a delete request Message along with the hash
   * of the data object the user wants deleted
   *
   * @param hash  The hash of the data to be deleted
   * @return      True if Message was sent successfully, false otherwise
   */
  public boolean deleteData(int hash) {
    if (client != null) {
      Message toSend = new Message(MessageType.DELETE, hash);
      return client.sendMessage(toSend);
    } 
    else return false;
  } 

  /**
   * Main method for running a Client. Ceates a new User and starts
   * the server and client with arguments provided 
   *
   * @param args [server port number] [client address] [client port number]
   */ 
  public static void main(String[] args) {
    String errMsg = "Please run this program with the following arguments:\n" +
                    "java ServiceProvider [server port number] [client address]" +
                    " [client port number]";
    if (args.length != 3) {
      System.out.println(errMsg);
      return;
    }

    Policy policy = new Policy(true, true);
    User user = new User(policy);
    
    //Start servers
    try {
      int serverPort = Integer.parseInt(args[0]);
      String cliAddress = args[1];
      int cliPort = Integer.parseInt(args[2]);
      user.startServers(serverPort, cliAddress, cliPort);
    } catch (NumberFormatException n) {
      System.out.println(n + "\n" + errMsg);
      return;
    }

    //Upload user data
    boolean sent = user.uploadData("This is some data...");
    if(sent) System.out.println("Data sent successfully!");
    else System.out.println("There was an error in sending the data");

    //Request sent data to be deleted

    user.deleteData(user.hashVals.get(0));
  }
}
