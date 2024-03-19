/**
 * Prielipp (265112)
 *
 * Tests the different versions of Hashes
 */

// ArrayList<>
import java.util.*;

public class TestHashers
{
  /**
   * Test the different has functions
   */
  public static void main(String[] args)
  {
    String iv = "GO_NAVY_2018^mid";

    ArrayList<Hasher> hashers = new ArrayList<Hasher>();
    hashers.add(new PadCutHasher());
    hashers.add(new ShiftPlusHasher(new Caesar(), iv));
    hashers.add(new ShiftPlusHasher(new Vigenere(), iv));
    hashers.add(new ShiftPlusHasher(new Clear(), iv));
    
    Scanner in = new Scanner(System.in);

    System.out.print("algorithm: ");
    String alg = in.next();

    System.out.print("password : ");
    String psw = in.next();

    System.out.println("password read: " + psw);

    for(Hasher h : hashers)
    {
      if(h.getAlgName().equals(alg))
      {
        try{
          System.out.println("hash computed: " + h.hash(psw));
        } catch(Throwable t)
        {
          System.out.println(t.getMessage());
        }
      }
    }
  }
}
