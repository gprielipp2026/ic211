/**
 * Prielipp (265112)
 *
 * User.java
 *
 * Stores:
 *  - username
 *  - hashing algorithm
 *  - hashed password
 *
 * Can only be created through the read method
 */

import java.util.*;
import java.io.*;

public class User implements Comparable<User>, Iterable<Data>
{
  private String username;
  private String hashedPswd;
  private Hasher algo;
  private String algorithmName;
  private String password = null;
  private ArrayList<Data> entries = null;
  private static ArrayList<Hasher> hashers = null;
  /**
   * Forces users to utilize read(Scanner)
   */
  private User()
  {
    username = null;
    hashedPswd = null;
    algo = null;
    entries = new ArrayList<Data>();
  }

  /**
   * Create a user from username, password, and hash algorithm name
   */
  public User(String username, String password, String algo) throws Throwable
  {
    this.username = username;
    this.algo = getHasher(algo);
    this.algorithmName = algo;
    this.hashedPswd = this.algo.hash(password); 
    this.password = password;
    this.entries = new ArrayList<Data>();
  }

  /**
   * Find the corresponding hash algorithm or thrwo and error if it
   * doesn't exist
   */
  private static Hasher getHasher(String algoName) throws Throwable
  {
    if(hashers == null)
    {
      hashers = new ArrayList<Hasher>();
      hashers.add( new PadCutHasher() );
      hashers.add( new FullShiftPlusHasher( new Caesar() ) );
      hashers.add( new FullShiftPlusHasher( new Vigenere() ) );
      hashers.add( new FullShiftPlusHasher( new Clear() ) );

    }
    boolean found = false;

    for(Hasher h : hashers)
    {
      if(h.getAlgName().equals(algoName))
      {
        found = true;
        return h;
      }
    }
    if(!found)
      throw new AlgorithmNotSupported("Hash", algoName); 

    return null;
  }

  /**
   * Read from the Scanner in the format:
   * <username> <algorithm> <hashedPswd>
   */
  public static User read(String fn, Scanner in) throws Throwable
  {
    User user = new User();

    try {
      user.username = in.next();
      user.algorithmName = in.next();
      user.hashedPswd = in.next();
      //user.algo = user.getHasher(user.algorithmName);
    } catch(NoSuchElementException e)
    {
      throw new WrongFormat(fn); 
    }


    return user;
  }

  /**
   * Hashes the password passed in
   * and sees if it is the same as the
   * saved hashed password 
   */
  public boolean authenticate(String password) throws Throwable
  {
    algo = User.getHasher(algorithmName);

    if( algo.hash(password).equals(hashedPswd) )
    {
      this.password = password;
      return true;
    }
    return false;
  }

  /**
   * Implement compareTo
   * -1 = this belongs BEFORE
   *  0 = this EQUALS other
   *  1 = this belongs AFTER
   */
  public int compareTo(User other)
  {
    return compareTo(other.username);
  }

  /**
   * Implement compareTo
   * -1 = this belongs BEFORE
   *  0 = this EQUALS other
   *  1 = this belongs AFTER
   */
  public int compareTo(String otherName)
  {
    return username.compareTo(otherName);
  }

  /**
   * Write the contents of the User to the file
   * using the PrintWriter
   */
  public void write(PrintWriter pw)
  {
    if(pw.checkError()) System.err.println("PW CLOSED !!!!");
    pw.println("user " + username + " " + algo.getAlgName() + " " + hashedPswd );
  }

  /**
   * add a piece of data if it matches
   */
  public boolean add(Data data)  
  {
    if(data.getUsername().equals(username))
    {
      entries.add(data);      
      return true;
    } 
    return false;
  }

  /**
   * Returns the username of the User
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Print the labels of the entries
   */
  public void printLabels() throws Throwable
  {
    for(Data entry : entries)
    {
      try{ entry.decrypt(password); System.out.println(entry.getLabel()); }
      catch (NoLabel nl) { System.out.println(nl.getMessage()); }
      catch (AlgorithmNotSupported ans) { System.out.println(ans.getMessage()); }
      catch (Throwable t) { throw t; }
    }
  }

  /**
   * Initializes this.algo from getHasher(algoName)
   */
  public void init() throws Throwable
  {
    algo = getHasher(algorithmName);
  }

  /**
   * Add a piece of data from
   * algName, label, value
   */
  public void addFrom(String algName, String label, String value) throws Throwable
  {
    for(int i = 0; i < entries.size(); i++)
    {
      if(entries.get(i).getLabel().equals(label))
      {
        entries.set(i, new Data(username, password, algName, label, value));
        return;
      }
    }

    entries.add( new Data(username, password, algName, label, value) );
  }

  /**
   * write Data with the PrintWriter
   */
  public void writeData(PrintWriter pw)
  {
    for(Data d : entries)
    {
      d.write(pw);
    }
  }

  /**
   * get entry from the data
   */
  public String getEntry(String label) throws Throwable
  {
    for(Data d : entries)
    {
      try { 
        d.decrypt(password);
        if(d.match(label))
          return d.getText();
      }
      catch (Throwable t) { /* do nothing */ }
    }
    return null;
  }

  //-----------------------------------------------------------------------------------
  public Iterator<Data> iterator()
  {
    return entries.iterator();
  }
}
