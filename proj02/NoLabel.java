/**
 * Prielipp (265112)
 *
 * No label exception 
 */

public class NoLabel extends Exception
{
  public NoLabel ()
  {
    super("data did not contain a label");
  }

  public NoLabel(String username, String label)
  {
    super(username + " doesn't have '" + label + "' as a label.");
  }
}

