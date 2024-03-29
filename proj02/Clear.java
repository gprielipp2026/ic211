// Encryptor providing the clear algorithm, which is to
// not change the plaintext at all.
public class Clear implements Encryptor {
  public String getAlgName() { return "clear";  }
  
  public void   init(char[] key) throws Throwable 
  {
    for(char cc : key)
    {
      int c = (int)cc;
      if(c < 42 || c > 122)
        throw new InvalidChar("key", cc); 
    }
  }

  public String encrypt(String plain) throws Throwable
  { 
    for(char cc : plain.toCharArray())
    {
      int c = (int)cc;
      if(c < 42 || c > 122)
        throw new InvalidChar("plaintext", cc); 
    }


    return plain;  
  }

  public String decrypt(String cipher) throws Throwable
  {
    for(char cc : cipher.toCharArray())
    {
      int c = (int)cc;
      if(c < 42 || c > 122)
        throw new InvalidChar("ciphertext", cc); 
    }

    return cipher;  
  }
}
