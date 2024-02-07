/**
 * Author: Prielipp (m265112)
 *
 * Schedule.java
 *
 * Implements adding sections to someone's schedule
 *
 * public methods:
 * Schedule()
 * void read(Scanner)
 * boolean add(String, int)
 * void printFits(String)
 * void printSections(String)
 * void printSchedule()
 * void printWeek()
 */

import java.util.*;

public class Schedule
{
  private Queue allCourses;
  private Queue selectedCourses;
  private Week selectedSchedule;

  /**
   * create a new queue
   */
  public Schedule()
  {
    allCourses = new Queue();
    selectedCourses = new Queue();
    selectedSchedule = new Week();
  }

  /**
   * reads from Scanner and fills out
   * all courses
   */
  public void read(Scanner sc)
  {
    Section course;
    while(sc.hasNext())
    {
      course = Section.read(sc);
      allCourses.enqueue(course);
    }
  }

  /**
   * adds a section to selectedCourses
   * if it exists in allCourses
   * returns true if able, false if failed
   */
  public boolean add(String courseName, int courseID)
  {
    int index = 0;
    Section tmp;
    
    while( (tmp = allCourses.at(index++)) != null )
    {
      if(tmp.equals(courseName, courseID))
      {
        selectedCourses.enqueue(tmp);
        // add the tmp "schedule" to the current weekly schedule
        selectedSchedule = selectedSchedule.add(new Week().fill(tmp.getCourseMtg()));
        return true;
      }
    }

    return false;
  }

  /**
   * Prints out all of the sections of the given course that 
   * could fit into the week; If the parameter="any", 
   * then print out all sections that fit into 
   * the week
   */
  public void printFits(String courseName)
  {
    int index = 0;
    boolean any = courseName.equals("any");
    Section tmp;

    while( (tmp = allCourses.at(index++)) != null )
    {
      // Either print anything that fits or it the courseNames match
      // and only if the course could fit into the selectedSchedule
      if ((courseName.equals(tmp.getCourseName()) || any) && 
          !selectedCourses.contains(tmp.getCourseName()) &&
          selectedSchedule.fits( tmp.getCourseMtg() ))
      {
        System.out.println(tmp);
      }
    }
  }

  /**
   * prints all of the sections in allCourses
   * as long as the courseName is equal to 
   * the passed in courseName
   */
  public void printSections(String courseName)
  {
    int index = 0;
    Section tmp;
    while( (tmp = allCourses.at(index++)) != null)
    {
      if(courseName.equals(tmp.getCourseName()))
        System.out.println(tmp);
    }
  }

  /**
   * prints all of the sections in allCourses
   */
  public void printSchedule()
  {
    int index = 0;
    Section tmp;
    while( (tmp = selectedCourses.at(index++)) != null) 
    {
      System.out.println(tmp);
    }
  }

  /**
   * prints the selectedCourses as a combined schedule in week
   */
  public void printWeek()
  {
    selectedSchedule.printGrid();
  }
}
