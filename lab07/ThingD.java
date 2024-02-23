import java.util.*;

public class ThingD extends ThingC
{
  public ThingD(int row, int col, char color)
  {
    super(row, col, color);
  }

  public void step()
  {
    final int[] dc = {0, 2, 0, -2}, dr = {-2, 0, 2, 0};
    row += dr[dir];
    col += dc[dir];
  }
}
