/**
 * Prielipp (265112)
 *
 * Data.java
 *
 * Stores a users encrypted data
 */

import java.util.*;
import java.io.*;

public class Data
{
  private String username;
  private Encryptor encalg;
  private String ciphertext;
  private String label;
  private String plaintext;
  private String encalgName;
  /**
   * Create a new Data from 
   * username, algName, label, value
   */
  public Data(String uname, String pswd, String algName, String label, String value) throws Throwable
  {
    username = uname;
    for(int i = 0; i < label.length(); i++)
    {
      int c = label.charAt(i);
      if(c < 42 || c > 122 || label.charAt(i) == '_')
        throw new BadLabel(label);
    }

    this.label = label;
    this.plaintext = value;
    encalg = getEncryptor(algName);
    encalg.init(pswd.toCharArray());
    ciphertext = encalg.encrypt(label + "_" + value);
  } 

  /**
   * Figure out which algorithm to use
   */
  private static ArrayList<Encryptor> algs = null;
  private static Encryptor getEncryptor(String algo) throws Throwable
  {
    if(Data.algs == null)
    {
      Data.algs = new ArrayList<Encryptor>();
      Data.algs.add( new Clear() );
      Data.algs.add( new Caesar() );
      Data.algs.add( new Vigenere() );
    }    
    boolean found = false;
    for(Encryptor alg : algs)
    {
      if(alg.getAlgName().equals(algo))
      {
        found = true;
        return alg;
      }
    } 
    if(!found)
      throw new AlgorithmNotSupported("Encryption", algo); 
    return null;
  }

  /**
   * Enforce using Data.read to create new objects
   */
  private Data()
  {

  }

  /**
   * Separate the label and the text after
   * decrypting the ciphertext
   */
  private void split(String decoded) throws Throwable
  {
    int ind = decoded.indexOf('_');

    if(ind == -1) // did not find it
      throw new NoLabel(ciphertext); 

    label = decoded.substring(0, ind);
    plaintext = decoded.substring(ind+1);
  }

  /**
   * Decrypt with the user's password
   */
  public String decrypt(String password) throws Throwable
  {
    encalg = getEncryptor(encalgName);
    encalg.init(password.toCharArray());
    split( encalg.decrypt(ciphertext) );

    return plaintext;
  }

  /**
   * If the labels match, return the ciphertext
   */
  public boolean match(String label)
  {
    return this.label.equals(label);
  }

  /**
   * Create data from a file in the format
   * <username> <encalg> <cipher-text>
   */
  public static Data read(Scanner in) throws Throwable
  {
    Data data = new Data();

    data.username = in.next();
    data.encalgName = in.next();
    //data.encalg = getEncryptor( in.next()  );
    data.ciphertext = in.next();

    return data; 
  }

  /**
   * Returns the username
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Returns the label
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * Returns the plaintext
   */
  public String getText()
  {
    return plaintext;
  }

  /**
   * Formatted:
   * data <username> <encalg> <cipher-text>
   */
  public void write(PrintWriter pw)
  {
    if(pw.checkError()) System.out.println("Data: it is closed?");
     
    pw.println("data " + username + " " + encalgName + " " + ciphertext);
  }

}
