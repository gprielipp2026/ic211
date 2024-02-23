import java.util.*; // for Random

public class Thing
{
  // dir: 0=North, 1=East, 2=South, 3=West. 
  protected int row, col, dir;
  protected char color;

  public Thing(int row, int col, char color)
  {
    this.row = row;
    this.col = col;
    this.dir = 0;
    this.color = color;
  }

  public void rightTurn()
  { 
    dir = (dir + 1) % 4; 
  }

  public void leftTurn() 
  { 
    dir = (dir + 3) % 4; 
  }

  public void maybeTurn(Random rand)
  {
    int i = rand.nextInt(3);
    if (i == 1) { rightTurn(); }
    if (i == 2) { leftTurn(); }      
  }

  public void step()
  {
    final int[] dc = {0,1,0,-1}, dr = {-1,0,1,0};
    row += dr[dir];
    col += dc[dir];
  }

  public String toString()
  {
    return row + " " + col + " " + color;
  }
}

