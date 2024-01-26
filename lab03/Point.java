import java.util.Scanner;

public class Point
{
  private double x;
  private double y;

  // initialize Point
  public Point(double x, double y)
  {
    this.x = x;
    this.y = y;
  }

  // reads an x and y value from the scanner
  // and returns the associated point
  public static Point read(Scanner sc)
  {
    double x = sc.nextDouble();
    double y = sc.nextDouble();
    return new Point(x,y);
  }
  
  // returns a string that looks like:
  // x y
  public String toString()
  {
    return x + " " + y;
  }

  // get access to the value in x
  public double getX()
  {
    return x;
  }

  // get access to the value in y
  public double getY()
  {
    return y;
  }

  public static Point min(Point a, Point b)
  {
    double minX = Math.min(a.getX(), b.getX());
    double minY = Math.min(a.getY(), b.getY());

    return new Point(minX, minY);
  }

  public static Point max(Point a, Point b)
  {
    double maxX = Math.max(a.getX(), b.getX());
    double maxY = Math.max(a.getY(), b.getY());
    
    return new Point(maxX, maxY);
  }

  public boolean within(Point min, Point max)
  {
    return this.getX() > min.getX() && this.getX() < max.getX() &&
           this.getY() > min.getY() && this.getY() < max.getY();
  }

  // maps a point between a min and max point
  // to the unit box (0,0) -> (1,1)
  public Point map(Point min, Point max)
  {
    if(!within(min, max))
    {
      return null;
    }

    double xLen = max.getX() - min.getX();
    double yLen = max.getY() - min.getY();
    double newX = (this.getX() - min.getX()) / xLen;
    double newY = (this.getY() - min.getY()) / yLen;

    return new Point(newX, newY);
  }

  // test the methods in Point class
  public static void main(String[] args)
  {
    System.out.println("POINT");
  }
}
