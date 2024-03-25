import java.util.*;
import java.io.*;

public class HW1
{
  public static void main(String[] args)
  {
    LineNumberReader r = null;
    if(args.length > 0)
    {
      try { r = new LineNumberReader(new FileReader(args[0])); }
      catch (Exception e) { e.printStackTrace(); }
    }
    else
      r = new LineNumberReader(new InputStreamReader(System.in));

    Scanner sc = new Scanner(r);
    try {
      System.out.println(Mystery.compute(sc));
    }
    catch(Exception e)
    {
      System.out.println("Error " + e.getMessage() + " at line " + r.getLineNumber());
    }
  }
}

