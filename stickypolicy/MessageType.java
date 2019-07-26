package stickypolicy;

/**
 * Enumerates possible types of Message between User
 * and ServiceProvider
 *
 * @author Lindsey Coffee-Johnson
 * @version 1.0
 */
enum MessageType {
  /**
   *  MessageType for uploading data
   */
  UPLOAD,
  /**
   * MessageType for sending a request to delete data
   */ 
  DELETE, 
  /**
   * MessageType for notifying User of an access to the User's data
   */
  NOTIFY, 
  /**
   * MessageType for acknowledging a delete request from a User
   */
  ACKNOWLEDGE;

}
