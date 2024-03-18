// Encryptor providing the clear algorithm, which is to
// not change the plaintext at all.
public class Clear implements Encryptor {
  public String getAlgName() { return "clear";  }
  public void   init(char[] key) { }
  public String encrypt(String plain) { return plain;  }
  public String decrypt(String cipher){ return cipher;  }
}
