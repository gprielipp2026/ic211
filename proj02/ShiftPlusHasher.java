/**
 * Prielipp (265112)
 *
 * ShiftPlusHasher.java
 *
 * Implements Hasher
 */

public class ShiftPlusHasher implements Hasher
{

  private Encryptor encryptor;
  private String iv; // initialization vector

  /**
   * Constructur (Encryptor)
   */
  public ShiftPlusHasher(Encryptor ext)
  {
    encryptor = ext;
    iv = "GO_NAVY_2018^mid";
  }

  /**
   * Constructor(Encryptor, String)
   */
  public ShiftPlusHasher(Encryptor extension, String initializationVector)
  {
    encryptor = extension;
    iv = initializationVector;
  }
  
  /**
   * Returns the algorithm's name
   */
  public String getAlgName()
  {
    return "shift+" + encryptor.getAlgName();
  }
 
  /**
   * Rotate x left by k amounts
   */
  private String shift(String x, int k)
  {
    String output = "";

    for(int i = 0; i < x.length(); i++)
    {
      int ind = (k + i) % x.length();
      output += x.charAt(ind);
    }

    return output;
  }

  /**
   * Shifts the encrypted output 16 times
   * encrypting between each iteration
   */
  public String hash(String password) throws Throwable
  {
    // this would throw something 
    encryptor.init(password.toCharArray());

    for(int i = 0; i < 16; i++)
    {
      int c = (int)iv.charAt(i);

      //if(c < 42 || c > 122)
      //throw new Throwable("Invalid char '" + iv.charAt(i) + "' " + i + " iterations into shift+" + encryptor.getAlgName());
      //// need to throw some error??

      int k = c % 16;
      iv = shift(iv, k);

      iv = encryptor.encrypt(iv);
    }

    return iv;
  }
}
