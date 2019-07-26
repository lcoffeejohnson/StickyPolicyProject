package stickypolicy;

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

    /**
     * Class constructor creates a new UserData object with provided
     * StickyHeader and hash and a timestamp at the time of creation.
     * Valid variable is set to True. 
     *
     * @param stickyHeader  The StickyHeader to store in this UserData object
     * @param hash          The hash of the StickyHeader to store
     */
    public UserData(StickyHeader stickyHeader, int hash) {
      ts = new Timestamp(System.currentTimeMillis());
      this.stickyHeader = stickyHeader;
      this.hash = hash;
      valid = true;
    }
  }

