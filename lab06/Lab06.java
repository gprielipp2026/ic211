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
    StringStack filters = new StringStack();

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
        String word = in.next();
        tq = tq.filter(true, word);
        filters.push("+" + word);
      } else if(cmd.equals("filter!"))
      {
        String word = in.next();
        tq = tq.filter(false, word);
        filters.push("-" + word);
      } else if(cmd.equals("reset"))
      {
        tq = constant;
      } else if(cmd.equals("undo"))
      {
        filters.pop();
        StringStack.Iter it = filters.iterator();
        tq = constant;
        while(it.hasNext())
        {
          String action = it.next();
          if(action.charAt(0) == '+')
          {
            tq = tq.filter(true, action.substring(1));
          }
          else
          {
            tq = tq.filter(false, action.substring(1));
          }
        }

      }

    } while(!cmd.equals("quit"));
  }

}
