/**
 * Stores a Queue of schedules and has a method for 
 * printint out the schedule
 * public methods:
 * Section()
 * void read(Scanner in)
 * boolean add(String className, int section)
 * void show()
 * void week()
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
   * return true if able to add the course
   * false if the course is unavailable to add to the 
   * schedule
   */
  public boolean add(String className, int section)
  {
    int index = this.locate(className, section);
    if(index == -1) 
      return false;

    courses.enqueue( allCourses.at(index) );
    return true;
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

  }
