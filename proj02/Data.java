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
  /**
   * Figure out which algorithm to use
   */
  private static ArrayList<Encryptor> algs = null;
  private static Encryptor getEncryptor(String algo) throws Throwable
  {
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
      throw new Throwable("Error! Encryption algorithm '" + algo + "' not supported.");
    return null;
  }

  /**
   * Enforce using Data.read to create new objects
   */
  private Data()
  {
    if(algs == null)
    {
      algs.add( new Clear() );
      algs.add( new Caesar() );
      algs.add( new Vigenere() );
    }
  }

  /**
   * Separate the label and the text after
   * decrypting the ciphertext
   */
  private void split(String decoded)
  {
    label = "";
    for(int i = 0; i < decoded.length(); i++)
    {
      char c = decoded.charAt(i);
      if(c == '_')
      {
        plaintext = decoded.substring(i+1);
        break;
      }

      label += c;
    }
  }

  /**
   * Decrypt with the user's password
   */
  public String decrypt(String password) throws Throwable
  {
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
    data.encalg = getEncryptor( in.next()  );
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
    pw.println("data " + username + " " + encalg.getAlgName() + " " + ciphertext);
  }
}
