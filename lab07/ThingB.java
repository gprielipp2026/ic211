import java.util.*;

public class ThingB extends Thing
{
  private int timeSinceLast = 0;

  public ThingB(int row, int col, char color)
  {
    super(row, col, color);
  }

  public void maybeTurn(Random rand)
  {
    int i = rand.nextInt(3);
    timeSinceLast++; 
    if (timeSinceLast == 10)
    {
      timeSinceLast = 0;
      if (i == 1) { rightTurn(); }
      if (i == 2) { leftTurn(); }
    }
  }
}
