
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
  
  public Message(MessageType type) {
    this.type = type;
  }

  public Message(MessageType type, int hash) {
    this.type = type;
    this.hash = hash;
  }

  public Message(MessageType type, StickyHeader stickyHeader, int hash) {
    this.type = type;
    this.stickyHeader = stickyHeader;
    this.hash = hash;
  }

  public MessageType getMessageType() {
    return type;
  }

  public StickyHeader getStickyHeader() {
    return stickyHeader;
  }

  public int getHash() {
    return hash;
  }

}
