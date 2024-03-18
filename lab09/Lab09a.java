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
      for(int i = 0; i < args.length; i++)
      {
        if(args[i].equals("-v")) verbose = true;
        else if(fn.equals("stdin")) fn = args[i];
      }
    }

    Scanner sc;
    
    if(fn.equals("stdin"))
      sc = new Scanner(System.in);
    else
    {
      try{ sc = new Scanner(new FileReader(fn)); }
      catch(IOException e)
      {
        System.out.println("File \'" + fn + "\' could not be opened; switching input to standard in.");
        fn = "stdin";
        sc = new Scanner(System.in);
      }
    }

    String prompt = "";
    if(fn.equals("stdin"))
        prompt = "> ";
  
    ModQueue Q = new ModQueue();

    do {
      System.out.print(prompt);
      String s = sc.next();
      String arg = null;
      try{
        if (s.equals("quit")) 
          break;
        else if (s.equals("clearto"))
        {
          arg = sc.next();
          Q.dequeue(arg);
        }
        else if (s.equals("add"))
        {
          arg = sc.next();
          Q.enqueue(arg);
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
      } 
      catch(NoSuchElementException e)
      {
        if(s.equals("add") || s.equals("clearto"))
          System.out.println("Unexpected end of input.");
        break;
      }
      catch(QueueEmptyException e)
      {
        if(verbose)
          System.out.println("String \'" + arg + "\' not found!");
      }
      catch(RuntimeException e)
      {
        // should do nothing
      }
      catch (Throwable e) {
        e.printStackTrace();
      }
    }while(true);
  }

}

