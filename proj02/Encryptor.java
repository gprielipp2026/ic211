// interface for objects that provide
// encryption functionality.
public interface Encryptor {
  public String getAlgName();
  public void   init(char[] key) throws Throwable;
  public String encrypt(String plain) throws Throwable;
  public String decrypt(String cipher) throws Throwable;
}

