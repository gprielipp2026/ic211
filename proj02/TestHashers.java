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
  public static void main(String[] args) throws Throwable
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

    System.out.println("password read : " + psw);

    boolean found = false;
    for(Hasher h : hashers)
    {
      if(h.getAlgName().equals(alg))
      {
        found = true;
        System.out.println("hash computed : " + h.hash(psw));
      }
    }

    if(!found)
      throw new AlgorithmNotSupported("Hashing", alg); 
  }
}
