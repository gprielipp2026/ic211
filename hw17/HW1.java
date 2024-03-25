import java.util.*;
import java.io.*;

public class HW1
{
  public static void main(String[] args)
  {
    Reader r = new InputStreamReader(System.in);
    Scanner sc = new Scanner(r);
    try {
      System.out.println(Mystery.compute(sc));
    }
    catch(Exception e)
    {
      System.out.println("Error " + e.getMessage());
    }
  }
}

