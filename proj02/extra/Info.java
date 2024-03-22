/**
 * Info that comes out of a Cracker
 */

public class Info
  {
    private String decodedPswd;
    private String key;
    
    public Info(String pswd, String key)
    {
      decodedPswd = pswd;
      this.key = key;
    }

    public String toString()
    {
      return decodedPswd + "<" + key + ">";
    }
  }

