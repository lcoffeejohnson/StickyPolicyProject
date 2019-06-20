
import java.io.Serializable;

public class StickyHeader implements Serializable {

  private String data = null;
  private Policy policy = null;  

  public StickyHeader(String data, Policy policy) {
    this.data = data;
    this.policy = policy;
  }
   
  public String getData() {
    return data;
  } 

  public Policy getPolicy() {
    return policy;
  }

}
