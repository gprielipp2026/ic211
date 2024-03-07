import java.util.*;
import java.io.*;

public class Lab09a
{

  public static void main(String[] args)
  {
    boolean verbose = false;
    String fn = "stdin";
    if(args.length > 0)
    {
      if(args[0].equals("-v")) verbose = true;
      else fn = args[0];
    }

    Scanner sc;

    if(fn.equals("stdin"))
      sc = new Scanner(System.in);
    else
    {
      try{ sc = new Scanner(new FileReader(fn)); }
      catch(IOException e)
      {
        System.out.println("File \'" + fn + "\' cound not be opened; switching input to standard in.");
        sc = new Scanner(System.in);
      }
    }


    ModQueue Q = new ModQueue();

    do {
      if(fn.equals("stdin"))
        System.out.print("> ");
      String s = sc.next();
      try{
        if (s.equals("quit")) 
          break;
        else if (s.equals("clearto"))
        {
          Q.dequeue(sc.next());
        }
        else if (s.equals("add"))
        {
          Q.enqueue(sc.next());
        }
        else if (s.equals("dump"))
        {
          System.out.println(Q.dump());
        }
        else
        {
          if(verbose)
            System.out.println("Unknown command \'" + s + "\'.");
        }
      } catch(NullPointerException e)
      {
        if(verbose)
          System.out.println("String \'" + s + "\' not found!");
      } catch(NoSuchElementException e)
      {
        if(s.equals("add") || s.equals("clearto"))
          System.out.println("Unexpected end of input.");
        break;
      }catch (Throwable e) {
        e.printStackTrace();
      }
    }while(true);
  }

}

