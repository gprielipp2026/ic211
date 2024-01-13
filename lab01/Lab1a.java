public class Lab1a
{
  /*
   * This calculates the first 10 squares
   * starting from 0
   */
  public static void main(String[] args)
  {
    int[] x = new int[10];
    int k = 0;
    for(;k < 10;k++) 
    {
      x[k] = k*k;
    }
    
    for (int j = 0; j < 10; j++) 
    {
      System.out.println(j + " squared is " + x[j]);
    } 
  }
}
