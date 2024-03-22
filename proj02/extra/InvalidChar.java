/**
 * Prielipp (265112)
 *
 * Invalid Character
 */

public class InvalidChar extends Exception
{
  public InvalidChar(String where, char bad)
  {
    super("Error! Invalid symbol '" + bad + "' in " + where + ".");
  }
}
