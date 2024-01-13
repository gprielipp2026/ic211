import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Lab1d
{
  public static int fileIndex;
  public static File commentsFile;
  public static ArrayList<String> comments;

  public static void loadFileToComments() throws FileNotFoundException
  {
    // basic variables for the stuff to work (init)
    fileIndex = 0;
    commentsFile = new File("snarky.txt");
    comments = new ArrayList<String>();

    if( commentsFile == null )
    {
      System.err.println("File didn't open");
      System.exit(1);
    }

    // read the file
    Scanner fileIn = new Scanner(commentsFile);
    if( fileIn == null )
    {
      System.err.println("Scanner didn't work");
      System.exit(1);
    }
    while(fileIn.hasNext())
    {
      comments.add( fileIn.nextLine() );
    }
    fileIn.close();
  }

  public static String getComment()
  {
    // not ideal, but oh well
    if(fileIndex == comments.size())
    {
      fileIndex = 0;
    } 

    return comments.get( fileIndex++ );
  }

  // it's a number guessing game!
  public static void main(String[] args)
  {
    // initialize the file
    try
    {
      loadFileToComments();
    }
    catch (FileNotFoundException e)
    {
      System.exit(1);
    } 

    // seed random
    Random rand = new Random(System.currentTimeMillis());

    // get a random number
    int numToGuess = rand.nextInt(11); // 0-10
    int totalNumGuess = 0;

    // Get the first guess
    Scanner in = new Scanner(System.in);
    System.out.print("Guess a number between 0 and 10: ");
    
    // game loop
    int guess;
    while(++totalNumGuess > 0) // an infinite loop
    { 
      guess = in.nextInt();

      // see if they got it
      if( guess != numToGuess )
      {
        String snark = getComment();
        System.out.print(snark + " Guess again: ");
      }
      else
      {
        break;
      }
    }
  
    in.close();

    System.out.println("Right after " + totalNumGuess + " guesses!");
  }
}
