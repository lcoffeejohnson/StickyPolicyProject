
import java.io.Serializable;

/**
 * This class stores user data along with the related user policy
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class StickyHeader implements Serializable {

  private String data = null;
  private Policy policy = null;  

  /**
   * Class constructor that creates a new StickyHeader with the
   * provided data and policy
   *
   * @param data    The String data to be stored in the StickyHeader
   * @param policy  The User Policy to be stored with the data
   */
  public StickyHeader(String data, Policy policy) {
    this.data = data;
    this.policy = policy;
  }

  /**
   * Class constructor which creates a copy of the given StickyHeader
   *
   * @param  stickyHeader The StickyHeader to create a copy of
   */
  public StickyHeader(StickyHeader stickyHeader) {
    this.data = stickyHeader.getData();
    this.policy = new Policy (stickyHeader.getPolicy());
  }  
   
  /**
   * Gets the data stored in the StickyHeader
   *
   * @return The data stored in the StickyHeader
   */
  public String getData() {
    return data;
  } 

  /**
   * Gets the Policy stored in the StickyHeader
   *
   * @return  The Policy stored in the StickyHeader
   */
  public Policy getPolicy() {
    return policy;
  }

  /**
   * Returns the String representation of the data stored in the StickyHeader
   *
   * @return  The String representation of the data
   */
  @Override
  public String toString() {
    return data;
  }
}
