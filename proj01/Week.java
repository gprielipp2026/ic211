/**
 * Author: Prielipp (m265112)
 *
 * Week.java
 *
 * Stores a grid of when a section meets
 *
 * public methods:
 * Week()
 * Week add(Week)
 * boolean fits(String)
 * void printGrid()
 * Week fill(String)
 */

import ic211.*;
import java.util.*;

public class Week
{
  // periods (6) x days (5)
  private boolean grid[][];

  public Week()
  {
    grid = new boolean[6][5];
    for(int p = 0; p < grid.length; p++)
    {
      for(int d = 0; d < grid[p].length; d++)
      {
        grid[p][d] = false;
      }
    }
  }

  /**
   * Creates a new Week
   * where the grids are combined
   */
  public Week add(Week other)
  {
    Week output = new Week();
    for(int p = 0; p < output.grid.length; p++)
    {
      for(int d = 0; d < output.grid[p].length; d++)
      {
        output.grid[p][d] = grid[p][d] || other.grid[p][d];
      }
    }
    return output;
  }


  /**
   * See if a given schedule in the format "MWF1,R12" fits
   * into the current week
   */
  public boolean fits(String schedule)
  {
    return fits( DrBrown.explode(schedule) );
  }

  /**
   *  See if a given schedule in the format ["M1", "W1", "F1", "R1", "R2"]
   *  will fit into the current week
   */
  private boolean fits(String[] schedule)
  {
    String days = "MTWRF";
    for(int i = 0; i < schedule.length; i++)
    {
      int day = days.indexOf(schedule[i].charAt(0));
      int period = schedule[i].charAt(1) - '0' - 1; // 1-6 -> 0-5
      
      if(grid[period][day]) 
        return false; // can't overlap with what is already there
    }
    return true;
  }

  /**
   * Displays the grid formatted like:
   *   M T W R F
   * 1 x x   x x
   * 2 x   x   x
   * 3 x x x x x
   * 4   x   x x
   * 5 x x x x x
   * 6 x x x x
   */ 
  public void printGrid()
  {
    System.out.println("  M T W R F");
    for(int p = 0; p < grid.length; p++)
    {
      System.out.print((p+1) + " ");
      for(int d = 0; d < grid[p].length; d++)
      {
        // x or ' '
        if(grid[p][d])
          System.out.print('x');
        else
          System.out.print(' ');

        // remove spaces before newlines
        if(d+1 < grid[p].length)
          System.out.print(' ');
      }
      System.out.println();
    }
  }

  /**
   * Fills the grid based on a string
   * formatted like:
   * MWF1,R12
   */
  public Week fill(String input)
  {
    fill( DrBrown.explode(input)  );
    return this;
  }

  /**
   * Fills the grid based on a String[]
   * like:
   * ["M1", "W1", "F1", "R1", "R2"]
   */
  private void fill(String[] dayPeriodPairs)
  {
    String days = "MTWRF";
    for(String pair : dayPeriodPairs)
    {
      int d = days.indexOf(pair.charAt(0));
      int p = pair.charAt(1) - '0' - 1; // '1'-'6' => 0->5
      grid[p][d] = true;
    }
  }

  /**
   * implemented for the test cases
   */
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    Week week = new Week();

    String cmd;
    do {
      week.printGrid();
      cmd = in.next().strip();
      if(cmd.equals("quit"))
        break;
      else
        week.fill(cmd);
    } while(!cmd.equals("quit"));
  }
}
