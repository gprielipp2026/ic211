/**
 * Author: Prielipp (m265112)
 *
 * Section.java
 *
 * Will store <Class> <Section> <Meeting Times> <Building Location>
 * 
 * public methods:
 * Section read(Scanner)
 * boolean equals(String, int)
 * String getCourseMtg()
 * String getCourseName;
 * String toString()
 */

import java.util.*;

public class Section
{
  private String  courseName;
  private int     courseID;
  private String  courseBldg;
  private String  courseMtg;

  /**
   * Enforce creation only through reading from a Scanner
   */
  private Section()
  {
    courseName = null;
    courseID = 0;
    courseBldg = null;
    courseMtg = null;
  }

  /**
   * Reads from the scanner in the format
   * <Class> <Section> <Meeting Timgs> <Building Location>
   */
  public static Section read(Scanner sc)
  {
    Section output = new Section();

    output.courseName = sc.next().strip();
    output.courseID = sc.nextInt();
    output.courseMtg = sc.next().strip();
    output.courseBldg = sc.next().strip();

    return output;
  }

  /**
   * Compare this to the simplified versions of a Section
   */
  public boolean equals(String courseName, int courseID)
  {
    return this.courseName.equals(courseName) && this.courseID == courseID;
  }

  /**
   * gets courseMtg
   */
  public String getCourseMtg()
  {
    return courseMtg;
  }

  /**
   * gets courseName
   */
  public String getCourseName()
  {
    return courseName;
  }

  /**
   * returns a string in format:
   * <Course> <Section> <Meeting Time> <Building Location>
   */
  public String toString()
  {
    return courseName + "\t" + courseID + "\t" + courseMtg + "\t" + courseBldg;
  }
}
