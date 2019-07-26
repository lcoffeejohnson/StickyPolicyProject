
import java.io.Serializable;

/**
 * This class creates Message objects which have all
 * of the necessary fields to be sent between a User
 * and a ServiceProvider
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class Message implements Serializable {

  private StickyHeader stickyHeader = null;
  private int hash = 0;
  private MessageType type = null;

  /**
   * Class constructor used only to send notifications between
   * User and ServiceProvider without any data
   *
   * @param type The MessageType of the message being sent
   */  
  public Message(MessageType type) {
    this.type = type;
  }

  /**
   * Class constructor for sending requests to delete the
   * data associated with the included hash
   *
   * @param type  The MessageType of the message being sent
   * @param hash  The hash of the data that the User is requesting be deleted
   */
  public Message(MessageType type, int hash) {
    this.type = type;
    this.hash = hash;
  }

  /**
   * Class constructor for uploading data
   *
   * @param type         The MessageType of the message being sent
   * @param stickyHeader The StickyHeader object containing the data being sent
   * @param hash         The hash of the StickyHeader object being sent
   */
  public Message(MessageType type, StickyHeader stickyHeader, int hash) {
    this.type = type;
    this.stickyHeader = stickyHeader;
    this.hash = hash;
  }

  /**
   * Returns the MessageType of the Message
   *
   * @return The MessageType of the Message
   */
  public MessageType getMessageType() {
    return type;
  }

  /**
   * Returns the StickyHeader contained in the Message
   *
   * @return The StickyHeader contained in the Message
   */
  public StickyHeader getStickyHeader() {
    return stickyHeader;
  }

  /**
   * Returns the hash of the StickyHeader contained in the Message
   *
   * @return The hash of the StickyHeader conatined in the Message
   */
  public int getHash() {
    return hash;
  }

}
