/**
 * Prielipp (265112)
 *
 * PadCutHasher.java
 *
 * implements Hasher
 */

public class PadCutHasher implements Hasher
{
  /**
   * Returns the algorithm's name
   */
  public String getAlgName()
  {
    return "padcut";
  }

  /**
   * Take the password and pad it out to 16 characters long
   */
  public String hash(String password) throws Throwable
  {
    String output = "";
    
    int i = 0; 
    while(output.length() < 16)
    {
      if(i < password.length())
      {
        int c = (int)password.charAt(i);
        if(c < 42 || c > 122)
          throw new InvalidChar("password", password.charAt(i)); 
        output += password.charAt(i);
 
      } 
      else
        output += 'x';

      i++;
    }

    return output;
  }
}
