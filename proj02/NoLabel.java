/**
 * Prielipp (265112)
 *
 * No label exception 
 */

public class NoLabel extends Exception
{
  public NoLabel (String corrupt)
  {
    super("Error! corrupted entry '" + corrupt + "' in vault file.");
  }

  public NoLabel(String username, String label)
  {
    super(username + " doesn't have '" + label + "' as a label.");
  }
}

