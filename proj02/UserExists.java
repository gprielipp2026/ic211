/**
 * Prielipp (265112)
 *
 * UserExists 
 */

public class UserExists extends Exception
{
  public UserExists(String username)
  {
    super("Error! Username '" + username + "' already in use.");
  }
}

