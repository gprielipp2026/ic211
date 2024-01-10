import java.util.Scanner;// for reading from input
import java.lang.Math;   // for the max function 

public class HW02 {
  public static void main(String[] args)
  {
    // the input stream
    Scanner input = new Scanner(System.in).useDelimiter(" |\r?\n|\r");
    // help from this regex to get it to quit after a newline (I only had the space)
    // https://stackoverflow.com/questions/1981497/java-scanner-question

    int numStrings = input.nextInt();
    String[] strings = new String[numStrings];

    // read the strings
    for(int i = 0; i < numStrings; i++)
    {
      strings[i] = input.next();
    }

    // close the stream
    input.close();

    // find how many lines this will cover
    int maxLines = maxLength(strings);

    // print out the strings
    for(int row = 0; row < maxLines; row++)
    {
      for(int col = 0; col < strings.length; col++)
      {
        if(row < strings[col].length())
        {
          System.out.print(strings[col].charAt(row) + " ");
        }
        else
        {
          System.out.print("  "); // 2x " "
        }
      }
      System.out.println();
    }
  }

  // return the length of the longest string
  public static int maxLength(String[] strings)
  {
    int longest = -1; // string's can't be negative length;
    
    for(int i = 0; i < strings.length; i++)
    {
      longest = Math.max(longest, strings[i].length());
    }

    return longest;
  }
}
