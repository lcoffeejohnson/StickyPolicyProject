package stickypolicy;

import java.io.Serializable;

/**
 * The Policy class stores User preferences regarding data access
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class Policy implements Serializable {

  //Examples of policy fields
  private boolean canShare = false;
  private boolean canCopy = false;

  /**
   * Class constructor for a policy with everything set to 
   * default. In this case, everything will be set to false.
   */
  public Policy() {
  }

  /**
   * Class constructor with policy fields set to given values
   *
   * @param canShare A boolean value for whether or not data can be shared
   *                 outside of the ServiceProvider which recieves it
   * @param canCopy  A boolean value for whether or not data can be copied
   */
  public Policy(boolean canShare, boolean canCopy) {
    this.canShare = canShare;
    this.canCopy = canCopy;
  }

  /**
   * Class constructor that will created a copy of the given policy object
   *
   * @param policy The Policy object to be copied
   */
  public Policy(Policy policy) {
    this.canShare = policy.getShare();
    this.canCopy = policy.getCopy();
  }

  /**
   * Sets the value of variable that allows sharing of the User's data
   * outside of the ServiceProvider
   *
   * @param canShare True if data can be shared, false otherwise
   */
  public void setShare(boolean canShare) {
    this.canShare = canShare;
  }
  
  /**
   * Sets the value of variable that allows copying of the User's data
   *
   * @param canCopy True if data can be copied, false otherwise
   */
  public void setCopy(boolean canCopy) {
    this.canCopy = canCopy;
  }

  /**
   * Returns value of boolean variable for whether or not a User's data can 
   * be shared outside of the Service Provider
   *
   * @return True if User data can be shared, false otherwise
   */
  public boolean getShare() {
    return canShare;
  }

  /**
   * Returns value of boolean variable for whether or not a User's data can 
   * be copied
   *
   * @return True if User data can be copied, false otherwise
   */
  public boolean getCopy() {
    return canCopy;
  }

}
