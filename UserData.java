
import java.sql.Timestamp;

/**
 * This class stores User data along with metadata.
 * Used in the ServiceProvider databse.
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
public class UserData {
    Timestamp ts = null;
    StickyHeader stickyHeader = null;
    int hash = 0;
    boolean valid = false;

    public UserData(StickyHeader stickyHeader, int hash) {
      ts = new Timestamp(System.currentTimeMillis());
      this.stickyHeader = stickyHeader;
      this.hash = hash;
      valid = true;
    }
  }

