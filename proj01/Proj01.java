/**
 * Proj01.java
 *
 * Main program in the project
 */

import java.util.*;
import java.io.*;

public class Proj01
{
  /**
   * Schedule manager reading from a list of all
   * sections and allowing the user to add sections
   * to the schedule and see how it impacts the week
   */
  public static void main(String[] args)
  {
    // make sure the correct arguments were passed in
    if(args.length != 1)
    {
      System.exit(1);
    }

    // create the Scanner
    Scanner file = null;
    try
    {
      file = new Scanner(new FileReader(args[0])); 
    } catch(IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }

    Scanner in = new Scanner(System.in);

    // Main loop
    String cmd;

    Schedule schedule = new Schedule();
    schedule.read(file);

    file.close();

    System.out.print("> "); 
    while(!(cmd = in.next()).equals("quit"))
    {
      if(cmd.equals("add"))
      {
        String courseName = in.next();
        int courseID = in.nextInt();
        if(!schedule.add(courseName, courseID))
        {
          System.out.println("Error! Section not found!");
        }
      }
      else if(cmd.equals("sections"))
      {
        String courseName = in.next().strip();
        schedule.printSections(courseName);
      }
      else if(cmd.equals("show"))
      {
        schedule.printSchedule();
      }
      else if(cmd.equals("week"))
      {
        schedule.printWeek();
      }
      else if(cmd.equals("fit"))
      {
        String courseName = in.next();
        schedule.printFits(courseName);
      }
      else
      {
        System.out.println("Unknown command: " + cmd);
      }
      System.out.print("> ");
    }  

    in.close();
  }
}
