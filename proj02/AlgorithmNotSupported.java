/**
 * Prielipp (265112)
 *
 * Algorithm not supported
 */

public class AlgorithmNotSupported extends Exception
{
  public AlgorithmNotSupported(String hashOrEncrypt, String algorithm)
  {
    super("Error! " + hashOrEncrypt + " algorithm '" + algorithm + "' not supported.");
  }
}

