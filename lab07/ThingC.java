import java.util.*;

public class ThingC extends Thing
{
  private int timeSinceLast = 0;
  private boolean isLeft = false;
  public ThingC(int row, int col, char color)
  {
    super(row, col, color);
  }

  public void maybeTurn(Random rand)
  {
    int i = rand.nextInt(3);
    timeSinceLast++; 
    if (timeSinceLast == 3)
    {
      timeSinceLast = 0;
      if (i == 1) { isLeft = false; }
      if (i == 2) { isLeft = true; }
    }
    if(isLeft) { leftTurn(); }
    else       { rightTurn();}

    isLeft = !isLeft;
  }
}
