public class HW5
{
  public static void main(String[] args)
  {
    Countdown counter = new Countdown(99,1);
    Countdown oneLess = new Countdown(98,0);
    String value = null;
    while(!counter.done() && !oneLess.done())
    {
      value = counter.next();
      
      String firstVal = value.substring(0,1).toUpperCase() + 
                        value.substring(1,value.length());
  
      String second = oneLess.next();
      if (second.equals("zero"))
      {
        second = "no more";
      }

      String bottle1 = "bottles";
      if(value.equals("one"))
      {
        bottle1 = "bottle";
      }

      String bottle2 = "bottles";
      if(second.equals("one"))
      {
        bottle2 = "bottle";
      }

      System.out.println(firstVal + " " + bottle1 + " of beer on the wall, " + 
                         value + " " + bottle1 + " of beer.");
      System.out.println("Take one down and pass it around, " + second +
                         " " + bottle2 + " of beer on the wall.");
      System.out.println();
    }
  }
}
