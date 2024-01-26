import java.lang.Math;

public class Box
{
  private Point min;
  private Point max;

  // constructor for Box taking a single point
  public Box(Point p) 
  {
    this.min = p;
    this.max = p;
  }

  // constructor for Box taking two initial points
  public Box(Point a, Point b)
  {
    this.min = Point.min(a, b); 
    this.max = Point.max(a, b); 
  }

  // expand the bounding box (if needed) to include point p
  public void growBy(Point p)
  {
    this.min = Point.min(min, p);
    this.max = Point.max(max, p);
  }

  // given point p in the bounding box, return associated
  // point in the unit square (see notes); return null if
  // p is not inside the bounding box.
  public Point mapIntoUnitSquare(Point p)
  {
    return p.map(min, max); 
  }

  // returns string representation like:
  // 2.0 < x < 9.0, 3.0 < y < 7.0
  public String toString()
  {
    return min.getX() + " < x < " + max.getX() + ", " +
           min.getY() + " < y < " + max.getY();
  }

  public static void main(String[] args)
  {
    System.out.println("BOX");
  }
}
