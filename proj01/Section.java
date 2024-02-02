/**
 * Will store <Class> <Section> <Meeting Times> <Building Location>
 * Will be searchable only by Class
 * public methods:
 * static Section read(Scanner sc)
 * boolean equals(String className, int section)
 * String toString()
 * boolean[][] getMtgTimesByDay()
 */

import java.util.*;

public class Section
{
  private String className;
  private int section;
  private boolean[][] mtgTimesByDay;
  private String mtgTimes;
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
    mtgTimes = null;
    bldgLocation = null;
    mtgTimesByDay = new boolean[5][6];
    for(int day = 0; day < mtgTimesByDay.length; day++)
    {
      for(int period = 0; period < mtgTimesByDay[day].length; period++)
      {
        mtgTimesByDay[day][period] = false;
      }
    }
  }

  /**
   * Fills in mtgTimesByDay according to mtgTimes
   * Ex: (using false=0 and true=1)
   * mtgTimes="MWF2,R12"
   * mtgTimesByDay=[
   *              //Periods:
   *              // 1 2 3 4 5 6
   *                [0 1 0 0 0 0], // monday     (M)
   *                [0 0 0 0 0 0], // tuesday    (T)
   *                [0 1 0 0 0 0], // wednesday  (W)
   *                [1 1 0 0 0 0], // thursday   (R)
   *                [0 1 0 0 0 0], // friday     (F)
   *               ]
   */
  private void fillMtgTimesByDay()
  {
    Scanner sc = new Scanner(mtgTimes).useDelimiter(",");
    String time;

    // loop through the given times
    while(sc.hasNext())
    {
      time = sc.next();

      int[] days = Section.parseDays(time);
      int[] periods = Section.parsePeriods(time);

      for(int i = 0; i < days.length; i++)
      {
        int day = days[i];
        for(int j = 0; j < periods.length; j++)
        {
          int period = periods[j] - 1; // 1-6 => 0-5

          this.mtgTimesByDay[day][period] = true;
        }
      }
    }
  }

  /**
   * Takes a String like "MWF2"
   * and returns int[0,2,4]
   * where
   * M => 0
   * T => 1
   * W => 2
   * R => 3
   * F => 4
   *
   * I wanted to use builtin lists but I'm too lazy to 
   * make my own class so I did this method instead
   */
  private static int[] parseDays(String time)
  {
    boolean days[] = {false, false, false, false, false};
    for(int i = 0; i < time.length(); i++)
    {
      char c = time.charAt(i);
      if(c == 'M')
      {
        days[0] = true;
      }
      else if(c == 'T')
      {
        days[1] = true;
      }
      else if(c == 'W')
      {
        days[2] = true;
      }
      else if(c == 'R')
      {
        days[3] = true;
      }
      else if(c == 'F')
      {
        days[4] = true;
      }
    }

    int numDays = 0;
    for(int i = 0; i < days.length; i++)
    {
      if(days[i])
      {
        numDays++;
      }
    }
    
    int parsed[] = new int[numDays];
    for(int i = 0; i < numDays; i++)
    {
      char c = time.charAt(i);
      if(c == 'M')
        parsed[i] = 0;
      else if(c == 'T')
        parsed[i] = 1;
      else if(c == 'W')
        parsed[i] = 2;
      else if(c == 'R')
        parsed[i] = 3;
      else if(c == 'F')
        parsed[i] = 4;
    }

    return parsed;
  }

  /**
   * Takes a String like "R12"
   * and returns int[1,2]
   */
  private static int[] parsePeriods(String time)
  {
    boolean days[] = {false, false, false, false, false,false};
    for(int i = 0; i < time.length(); i++)
    {
      char c = time.charAt(i);
      if(c == '1')
      {
        days[0] = true;
      }
      else if(c == '2')
      {
        days[1] = true;
      }
      else if(c == '3')
      {
        days[2] = true;
      }
      else if(c == '4')
      {
        days[3] = true;
      }
      else if(c == '5')
      {
        days[4] = true;
      }
      else if(c == '6')
      {
        days[5] = true;
      }
    }

    int numDays = 0;
    for(int i = 0; i < days.length; i++)
    {
      if(days[i])
      {
        numDays++;
      }
    }

    int parsed[] = new int[numDays];
    for(int i = 0; i < numDays; i++)
    {
      char c = time.charAt(time.length()-numDays + i);
      parsed[i] = c - '0';
    }

    return parsed;

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
    output.mtgTimes = in.next();
    output.bldgLocation = in.next();

    output.fillMtgTimesByDay();

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
   * Returns mtgTimesByDays
   */
  public boolean[][] getMtgTimesByDay()
  {
    return mtgTimesByDay;
  }

  /**
   * Test my methods
   */
  public static void main(String[] args)
  {
    String test = "MWF1256";
    int days[] = Section.parseDays(test);
    int periods[] = Section.parsePeriods(test);

    for(int i : days)
    { 
      System.out.print(i + " ");
    }
    System.out.println();
    for(int i : periods)
    {
      System.out.print(i + " ");
    }
    System.out.println();
  }
}
