/**
 * Stores a Queue of schedules and has a method for 
 * printint out the schedule
 */

import java.util.*;

public class Schedule
{
  private Queue allCourses;
  private Queue courses;

  public Section()
  {
    allCourses = new Queue();
    courses = new Queue();
  }

  /**
   * Read from a file and fill out allCourses
   */
  public void read(Scanner in)
  {
    while(in.hasNext())
    {
      Section section = Section.read(in);
      allCourses.enqueue(section);
    }
  }

  /**
   * Return the index of a given className in allClasses
   * or -1 if it doesn't exist
   */
  private int locate(String className, int section)
  {
    int index = 0;
    Section tmp;
    while((tmp = allCourses.at(index)) != null)
    {
      if(tmp.equals(className, section))
        return index;
      index++;
    }
    return -1;
  }

  /**
   * Add a section from allCourses to courses
   */
  public boolean add(String className, int section)
  {
    int index = this.locate(className, section);
    if(index == -1) 
      return false;

    courses.enqueue( allCourses.at(index) );
  }

  /**
   * Print all of the sections in a row
   */
  public void show()
  {
    int index = 0;
    Section tmp;
    while((tmp = allCourses.at(index++)) != null)
    {
      System.out.println(tmp);  
    }
  }

  /**
   * Print out the schedule based on courses
   * in the format:
   *   M T W R F
   * 1       x
   * 2 x     x x
   * 3       x
   * 4 x   x x
   * 5   x x   x
   * 6 x x x   x
   */
  public void week()
  {
    System.out.println("  M T W R F");

    int index = 0;
    Section tmp;
    boolean gridSchedule[][] = new boolean[5][6];
    for(int i = 0; i < gridSchedule.length; i++)
    {
      for(int j = 0; j < gridSchedule[j].length; j++)
      {
        gridSchedule[i][j] = false;
      }
    }

    while((tmp = courses.at(index++)) != null)
    {
      boolean mtgTimesByDay[][] = tmp.getMtgTimesByDay();
      for(int day = 0; day < mtgTimesByDay.length; day++)
      {
        for(int period = 0; period < mtgTimesByDay[day].length; period++)
        {
          gridSchedule[day][period] = gridSchedule[day][period] || 
                                      mtgTimesByDay[day][period];
        }
      }
    }

    for(int period = 0; period < gridSchedule[0].length; period++)
    {
      System.out.print((period+1)+" ");
      for(int day = 0; day < gridSchedule.length; day++)
      {
        if(gridSchedule[day][period])
          System.out.print("x");
        else
          System.out.print(" ");
        
        if(day+1 < gridSchedule.length)
          System.out.print(" ");
      }
      System.out.println();
    } 
  }
}
