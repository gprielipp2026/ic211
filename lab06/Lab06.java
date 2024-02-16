/**
 * Nate Schmidt (265646) + George Prielipp (265112)
 * The main command line interface to filter a bunch of tweets
 */

import ic211.*;
import java.util.*;
import java.io.*;

public class Lab06 {
  public static void main(String[] args)
  {
    if(args.length != 1)
    {
      System.out.println("usage: java Lab06 <filename>");
      System.exit(1);
    }

    Scanner fin = null;
    try{
      fin = new Scanner(new FileReader(args[0]));
    } catch(IOException e) { e.printStackTrace(); }
  
    // read all of the tweets into the Queue
    TQE2 tq = new TQE2();
    TQE2 constant;

    Tweet tweet;
    while( (tweet = Tweet.read(fin)) != null ) { tq.enqueue(tweet); }
    fin.close();

    constant = tq;

    // main loop
    Scanner in = new Scanner(System.in);
    String cmd;
    do {
      System.out.println(tq.getLength() + " tweets");
      System.out.print("> ");
      cmd = in.next();

      // different commands
      if(cmd.equals("dump"))
      {
        tq.dump();
      } else if(cmd.equals("filter"))
      {
        tq = tq.filter(true, in.next());
      } else if(cmd.equals("filter!"))
      {
        tq = tq.filter(false, in.next());
      } else if(cmd.equals("reset"))
      {
        tq = constant;
      }

    } while(!cmd.equals("quit"));
  }

}
