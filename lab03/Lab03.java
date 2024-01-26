import java.util.Scanner;

public class Lab03
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);

    // the bounding box
    Box bound = null;

    // get the next command
    String cmd;
    while( !(cmd = in.next()).equals("done") )
    {
      // add a point
      if( cmd.equals("add") )
      {
        Point p = Point.read(in);
        if(bound == null)
        {
          bound = new Box(p);
        }
        else
        {
          bound.growBy(p);
        }
      }
      // print the box
      else if( cmd.equals("box") )
      {
        System.out.println(bound);
      }
      // map and print the point
      else if( cmd.equals("map") )
      {
        Point p = Point.read(in);
        Point mapped = bound.mapIntoUnitSquare(p);
        if(mapped != null)
        {
          System.out.println(mapped);
        }
        else
        {
          System.out.println("error");
        }
      }
      // bad input
      else
      {   
        System.out.println("Error! Unknown command \"" + cmd + "\"!");
      }
    } // end while
  } // end main
}
