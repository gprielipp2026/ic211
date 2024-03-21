/**
 * Prielipp (265112)
 *
 * WrongFormat (file is bad) 
 */

public class WrongFormat extends Exception
{
  public WrongFormat(String filename)
  {
    super("Error! File '" + filename + "' improperly formatted.");
  }
}

