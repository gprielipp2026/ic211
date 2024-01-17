import java.util.*;

public class HW3
{
  // create a mid from input
  // alpha fName lName Co.
  public static Mid createMid(Scanner in)
  {
    Mid mid = new Mid();

    mid.alpha = in.next();
    mid.firstName = in.next();
    mid.lastName = in.next();
    mid.company = in.nextInt();

    return mid;
  }

  // print a Mid to output
  // alpha fName lName Co.
  public static void printMid(Mid mid)
  {
    System.out.println(mid.alpha + " " +
                       mid.firstName + " " +
                       mid.lastName + " " +
                       mid.company);
  }

  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);

    // reads in a number of mids
    int numMids = in.nextInt();

    // reads info for that many mids
    Mid[] mids = new Mid[numMids];
    for(int i = 0; i < numMids; i++)
    {
      mids[i] = createMid(in);
    }

    // reads in a company number
    int company2Search = in.nextInt();

    // prints the info for mids in that company in order of those read in
    for(int i = 0; i < numMids; i++)
    {
      if(mids[i].company == company2Search)
      {
        printMid(mids[i]);
      }
    }
  }
}
