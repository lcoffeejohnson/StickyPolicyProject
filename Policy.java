
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

  public Policy() {
  }

  public Policy(boolean canShare, boolean canCopy) {
    this.canShare = canShare;
    this.canCopy = canCopy;
  }

  public Policy(Policy policy) {
    this.canShare = policy.getShare();
    this.canCopy = policy.getCopy();
  }

  public void setShare(boolean canShare) {
    this.canShare = canShare;
  }
  
  public void setCopy(boolean canCopy) {
    this.canCopy = canCopy;
  }

  public boolean getShare() {
    return canShare;
  }

  public boolean getCopy() {
    return canCopy;
  }

}
