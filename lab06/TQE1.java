/**
 * Nate Schmidt (265646) George Prielipp (265112) * Allows you to count how many tweets are in the queue * and iterate over it * and remove items from the queue based on a filter
 */

import ic211.*;

public class TQE1 extends TweetQueue {
  private int length;

  /**
   * Base constructor
   */
  TQE1()
  {
    super();
    length = 0;
  }

  /**
   * Adds a tweet to the end of the queue
   */
  public void enqueue(Tweet s)
  {
    super.enqueue(s);
    length++;
  }

  /**
   * Removes the top tweet
   */
  public Tweet dequeue()
  {
    Tweet t = super.dequeue();
    length--;
    return t;
  }

  /**
   * Gets the length
   */
  public int getLength()
  {
    return length;
  }

  /**
   * Dump the contents of the queue to stdout
   */
  public void dump()
  { 
    // iterate through the queue
    TweetQueue.Iter it = this.iterator();
    while(it.hasNext())
    {
      System.out.println(it.next());
    }
  }
}
