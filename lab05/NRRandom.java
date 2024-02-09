/**
 * Prielipp (265112)
 * Non-Repeating Random makes sure that nextInt does not produce the same value
 * consecutively
 */

import java.util.*;

public class NRRandom extends Random 
{
  private int prev;

  /**
   * contstructor
   */
  public NRRandom() { super(); prev = -1; }
  public NRRandom(long seed) { super(seed); prev = -1; }

  /**
   * overridden nextInt(bound)
   * gets a the next non-consecutive int
   */
  public int nextInt(int bound)
  {
    int next = super.nextInt(bound);
    if(prev == next)
      return nextInt(bound);
    prev = next;
    return next;
  }
}
