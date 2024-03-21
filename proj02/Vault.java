/**
 * Prielipp (265112)
 *
 * Vault.java
 *
 * Takes a vault file and alows users to authenticate
 * prints "Access granted!" or "Access denied!"
 */

import java.util.*;
import java.io.*;

public class Vault
{
  private ArrayList<User> users;
  private User authenticatedUser;

  /**
   * Forces users to utilize read(Scanner) to create a vault
   */
  private Vault() 
  {
    users = new ArrayList<User>();
  }

  /**
   * Read from a vault file and create User's
   */
  public static Vault read(String fn, Scanner in) throws Throwable
  {
    Vault vault = new Vault();

    User nextUser = null;
    while( in.hasNext() )
    {
      String type = in.next();

      if(type.equals("user"))
      {
        nextUser = User.read(fn, in);
        vault.users.add( nextUser );
      } 
      else if (type.equals("data"))
      {
        Data data = Data.read(in);
        // I'm assuming that user's information come before their data
        for(User user : vault.users)
        {
          if(user.getUsername().equals(data.getUsername()))
            user.add(data);
        }
      } 
    }

    return vault;
  }

  /**
   * Go through the list of user's and authenticate the username
   * with the password. Return true if the User authenticates
   * False otherwise
   */
  public boolean authenticate(String username, String password) throws Throwable
  {
    for(User user : users)
    {
      // make sure it has the proper algorithm information ready
      user.init();
      if(user.compareTo(username) == 0 &&
          user.authenticate(password))
      {
        authenticatedUser = user;

        for(Data d : authenticatedUser)
        {
          d.decrypt(password);
        }

        return true;
      } 
    }
    return false;
  }

  /**
   * Loops through users and sees if the passed in user
   * already exists
   */
  public void verify(User user, String username) throws Throwable
  {
    // make sure the user doesn't exist already
    for(User other : users)
    {
      if(user.compareTo(other) == 0)
        throw new UserExists(username); 
    }
  }

  /**
   * add a user to the vault
   */
  public void add(User user)
  {
    users.add(user);
  }

  /**
   * Write all of the users to the file
   * with the PrintWriter
   */
  public void write(PrintWriter pw) throws Throwable
  {
    for(User usr : users)
    {
      usr.init();
      usr.write(pw);
    }
  }

  /**
   * Get the entry from the authenticated user
   */
  public String getEntry(String label) throws Throwable
  {
    for(Data data : authenticatedUser)
    {
      if(data.match(label)) return data.getText();
    }
    throw new NoLabel(authenticatedUser.getUsername(), label); 
  }

  /**
   * Print the labels of the authenticated User
   */
  public void printLabels()
  {
    authenticatedUser.printLabels();
  }

  /**
   * Test's Vault application
   * Usage: java Vault <vault-file>
   */
  public static void main(String[] args) throws Throwable
  {
    // parse the arguments for flags
    // and for the vault-file
    int fileInd = -1;
    boolean flags = false;
    for(int i = 0; i < 2 && i < args.length; i++)
    {
      if(args[i].charAt(0) == '-')
      {
        flags = true;
      }
      else
      {
        fileInd = i;
      }
    }

    // display the usage if the arguments were messed up
    if(fileInd == -1) 
    {
      System.out.println("usage: java Vault [-au] <filename>");
      System.exit(1);
    }

    // read from the vault file
    Scanner vfIn = null;
    try { vfIn = new Scanner(new FileReader(args[fileInd])); }
    catch (IOException e) { System.out.println("Error! File '" + args[fileInd] + "' could not be opened."); System.exit(1); }

    // essential vault for program
    Vault vault = Vault.read(args[fileInd], vfIn);

    // variables for main loop
    Console pswdIn = System.console();
    String username;
    String password;
    String cmd;

    // get the user information 
    System.out.print("username: ");
    username = pswdIn.readLine();

    System.out.print("password: ");
    password = new String(pswdIn.readPassword());

    Scanner in = new Scanner(System.in);

    // if they are adding a user:
    // add the user
    try{
      if(flags)
      {
        System.out.print("Hash algorithm: ");
        //String algo = in.next();
        String algo = pswdIn.readLine();
        User user = null;

        user = new User(username, password, algo);  

        vault.verify(user, username);
        PrintWriter pw = null;
        try{ pw = new PrintWriter(new File(args[fileInd])); }
        catch(FileNotFoundException e) { e.printStackTrace(); }

        // write the user to the file
        vault.add(user);

        vault.write(pw);

        if(pw != null)
          pw.close();

        System.exit(0);
      }

      // authenticate them
      if(vault.authenticate(username, password))
      {
        System.out.println("Access granted!");
      }
      else
      {
        System.out.println("Access denied!");
        System.exit(1);
      }

      // main loop
      do
      { 
        // get the next command
        System.out.print("> ");
        cmd = in.next();

        // handle the different commands
        if(cmd.equals("labels"))
        {
          // print all available labels in the user's entries
          vault.printLabels();
        } 
        else if(cmd.equals("get"))
        {
          // get the entry from a user
          String label = in.next();
          System.out.println( vault.getEntry(label) ); 
        }
        else if(!cmd.equals("quit"))
        {
          System.out.println("Unknown command '" + cmd + "'.");
        }
      } while(!cmd.equals("quit"));

    } catch(AlgorithmNotSupported exc) {
      System.out.println(exc.getMessage());
      System.exit(1);
    } catch(InvalidChar ic) {
      System.out.println(ic.getMessage());
      System.exit(1);
    } catch(UserExists ue) {
      System.out.println(ue.getMessage());
      System.exit(1);
    } 
    // end catch
  }// end main
}// end class
