/**
 * Represents the in a matrix of what is supposed to be happening
 * public methods:
 * boolean[][] getMtgTimesByDay()
 * String toString()
 */



public class Week
{
  private boolean[][] mtgTimesByDay;
  private String mtgTimes;  
  public Week()
  {
    mtgTimes = null;
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
  public void fillMtgTimesByDay(String mtgTimes)
  {
    this.mtgTimes = mtgTimes;
    Scanner sc = new Scanner(mtgTimes).useDelimiter(",");
    String time;

    // loop through the given times
    while(sc.hasNext())
    {
      time = sc.next();

      int[] days = Week.parseDays(time);
      int[] periods = Week.parsePeriods(time);

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
   * Return the mtgTimes that is passed in 
   * when parsing into the boolean[][]
   */
  public String toString()
  {
    return mtgTimes;
  }

  /**
   * Returns mtgTimesByDays
   */
  public boolean[][] getMtgTimesByDay()
  {
    return mtgTimesByDay;
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
  public void printGrid(Queue courses)
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
