/**
 * Prielipp (265112)
 *
 * Vigenere.java
 *
 * Implements Encryptor
 *
 * Public methods:
 * String getAlgName()
 * void init(char[] key)
 * String encrypt(String plain)
 * String decrypt(String cipher)
 */

public class Vigenere implements Encryptor
{
  private char[] key = null;

  /**
   * Returns the algorithms name
   */
  public String getAlgName()
  {
    return "vigenere";
  }

  /**
   * Initializes the key for the algorithm
   */
  public void init(char[] key) throws Throwable
  {
    for(char k_i : key)
    {
      int valk_i = (int) k_i;
      if(valk_i < 42 || valk_i > 122)
        throw new Throwable("init: char '" + k_i + "' invalid");
    }
    this.key = key;
  }

  /**
   * Encrypts the plaintext into ciphertext
   * with the key using a Vigenere shift
   */
  public String encrypt(String plain) throws Throwable
  {
    char[] cipher = plain.toCharArray();

    for(int i = 0; i < cipher.length; i++)
    {
      char pc = cipher[i];
      char sc = key[i % key.length];

      int k = sc - 42;
      int p = pc - 42;

      if(p < 0 || p > 80)
        throw new Throwable("encrypt: char '" + pc + "' invalid");

      int c = (p + k) % 81;
      char cc = (char)(42 + c);

      cipher[i] = cc;
    }

    return new String(cipher);  
  }

  /**
   * Decrypts the ciphertext into plaintext
   * with the key using a Vigenere shift
   */
  public String decrypt(String cipher) throws Throwable
  {
    char[] plain = cipher.toCharArray();

    for(int i = 0; i < plain.length; i++)
    {
      char cc = plain[i];
      char sc = key[i % key.length];
    
      int k = sc - 42;
      int c = cc - 42;

      if(c < 0 || c > 80) // between 42-122
        throw new Throwable("decrypt: char '" + cc + "' invalid");

      int p = (c + (81 - k)) % 81;
      char pc = (char)(42 + p);

      plain[i] = pc;
    }

    return new String(plain);    
  }

  /**
   * Testing Vigenere shift
   */
  public static void main(String[] args)
  {
    if(args.length != 3)
    {
      System.out.println("Usage: java Vigenere <key> <(e)ncrypt/(d)ecrypt> <message>");
      System.exit(1);
    }

    Vigenere c = new Vigenere();
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
