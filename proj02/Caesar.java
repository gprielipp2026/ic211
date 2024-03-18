/**
 * Prielipp (265112)
 * Caesar.java
 *
 * Implements Encryptor
 *
 * Public methods:
 * String getAlgName()
 * void init(char[] key)
 * String enccrypt(String plain)
 * String decrypt(String cipher)
 */

public class Caesar implements Encryptor
{
  private int shift = 0;

  /**
   * Returns the algorithms name
   */
  public String getAlgName()
  {
    return "caesar";
  }

  /**
   * Initializes the key for the algorithm
   */
  public void init(char[] key) throws Throwable
  {
    // shift = 42 + ()sum(18, k_i - 42) % 81)
    shift = 18;
    for(char k_i : key)
    {
      int valk_i = (int) k_i;
      if(valk_i < 42 || valk_i > 122)
        throw new Throwable("init: char '" + k_i + "' invalid");
      shift += valk_i - 42;
    }
    shift = shift % 81;
    shift += 42;
  }

  /**
   * Encrypts the plaintext into ciphertext
   * with the key using a Caesar shift
   */
  public String encrypt(String plain) throws Throwable
  {
    char[] cipher = plain.toCharArray();

    for(int i = 0; i < cipher.length; i++)
    {
      char pc = cipher[i];

      int k = shift - 42;
      int p = (int)pc - 42;
      if(p < 0 || p > 80) // between 42-122
        throw new Throwable("encrypt: char '" + pc + "' invalid");

      int c = (p + k) % 81;
      char cc = (char)(42 + c);

      cipher[i] = cc;
    }

    return new String(cipher);  
  }

  /**
   * Decrypts the ciphertext into plaintext
   * with the key using a Caesar shift
   */
  public String decrypt(String cipher) throws Throwable
  {
    char[] plain = cipher.toCharArray();

    for(int i = 0; i < plain.length; i++)
    {
      char cc = plain[i];

      int k = shift - 42;
      int c = (int)cc - 42;
      if(c < 0 || c > 80) // between 42-122
        throw new Throwable("decrypt: char '" + cc + "' invalid");

      int p = (c + (81 - k)) % 81;
      char pc = (char)(42 + p);

      plain[i] = pc;
    }

    return new String(plain);    
  }

  /**
   * Testing Caesar shift
   */
  public static void main(String[] args)
  {
    if(args.length != 3)
    {
      System.out.println("Usage: java Caesar <key> <(e)ncrypt/(d)ecrypt> <message>");
      System.exit(1);
    }

    Caesar c = new Caesar();
    try{
      c.init(args[0].toCharArray());

      if(args[1].equals("e"))
      {
        System.out.println("Encrypted(" + args[0] + ", " + args[2] + ") = " + c.encrypt(args[2]));
      }
      else if(args[1].equals("d"))
      {
        System.out.println("Decrypted(" + args[0] + ", " + args[2] + ") = " + c.decrypt(args[2]));
      } 

    } catch(Throwable e)
    {
      System.out.println(e.getMessage());
    }
  }
}
