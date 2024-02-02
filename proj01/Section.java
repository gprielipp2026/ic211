/**
 * Will store <Class> <Section> <Meeting Times> <Building Location>
 * Will be searchable only by Class
 * public methods:
 * static Section read(Scanner sc)
 * boolean equals(String className, int section)
 * String toString()
 */

import java.util.*;

public class Section
{
  private String className;
  private int section;
  private Week mtgTimes;
  private String bldgLocation;

  /**
   * I don't want anyone to be able to create a 
   * new Section. Only call the read method
   * Make sure everything is initialized to a "zero"
   */
  private Section() 
  {
    className = null;
    section = 0;
    bldgLocation = null;
    mtgTimes = new Week();
  }


  

  /**
   * Takes a Scanner and reads from input in the format:
   * <Class> <Section> <Meeting Times> <Building Location>
   * Ex:
   * IC211  2004  MW2,R12 MI302
   * Note: Fields to be read in are tab separated (trusting
   * the Scanner to be passed in correctly)
   */
  public static Section read(Scanner in)
  {
    Section output = new Section();
    
    output.className = in.next();
    output.section = in.nextInt();
    String mtgTimesStr = in.next();
    output.bldgLocation = in.next();

    mtgTimes.fillMtgTimesByDay(mtgTimesStr);

    return output;
  }

  /**
   * Determine if this className is equal to 
   * another className and section
   */
  public boolean equals(String className, int section)
  {
    return className.equals(this.className) && this.section == section;
  }

  /**
   * Output in the form
   * <Class> <Section> <Meeting Times> <Building Location>
   */
  public String toString()
  {
    return className + "\t" + section + "\t" + mtgTimes + "\t" + bldgLocation;
  }

   /**
   * Test my methods
   */
  public static void main(String[] args)
  {
    String test = "MWF1256";
    
  }
}
