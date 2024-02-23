import java.util.*;

public class ThingE extends ThingC
{
  private int prow, pcol;
  private int speed = 6;
  public ThingE(int row, int col, char color)
  {
    super(row, col, color);
    prow = row;
    pcol = col;
  }

  public void step()
  {
    for(int i = 0; i < speed; i++)
    {
      super.step();
      if(i < speed-1) 
        System.out.println(this);
    }
  }

  // public String toString()
  // {
  //   String ret = prow + " " + pcol + " " + color + "\n" + super.toString();
  //   prow = row;
  //   pcol = col;
  //   return ret;
  // }
}
