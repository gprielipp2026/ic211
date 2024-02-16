/**
 * Nate Schmidt (265646) George Prielipp (265112) * Allows you to count how many tweets are in the queue * and iterate over it * and remove items from the queue based on a filter
 */

import ic211.*;

public class TQE2 extends TQE1 {
  /**
   * Base constructor
   */
  TQE2()
  {
    super();
  }

  /**
   * Filters tweets for a specific word 
   */
  public TQE2 filter(boolean isWhitelist, String word)
  {
    TQE2 filtered = new TQE2();
    Iter it = iterator();

    while(it.hasNext())
    {
      Tweet tweet = it.next();
      if(isWhitelist == (tweet.getText().indexOf(word) != -1))
      {
        filtered.enqueue(tweet);
      }
    }

    return filtered;
  }
}
