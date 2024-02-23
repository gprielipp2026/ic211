import java.util.*;

public class Lab07c
{
  public static void main(String[] args)
  {
    int N = 200;
    if (args.length != 0) N = Integer.parseInt(args[0]);
    Random rand = new Random(System.currentTimeMillis());
 
    LList L = new LList();
    int count = 0;
    while(true) {
      // every N rounds add another typeA and typeB Thing
      if (count % N == 0)
	{
	  // add a typeA thing to the list
	  Thing tA = new Thing(45, 50, 'r'); 
	  L.add(tA);	
        
	  // add a typeB thing to the list
	  Thing tB = new ThingB(55, 50, 'b'); 
	  L.add(tB);


          Thing tC = new ThingC(50, 50, 'g');
          L.add(tC);
	}

      // print out each thing
      Thing t;
      for(LList.Iter it = L.iterator(); it.hasNext(); )
	{
	  t = it.next();
	  System.out.println(t);
	  t.maybeTurn(rand);
	  t.step();
	}
      System.out.println("done");
      System.out.flush();

      count++;
    }
  }
}
