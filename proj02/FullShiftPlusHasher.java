/**
 * Prielipp (265112)
 *
 * FullShiftPlusHasher
 *
 * Extends ShiftPlusHasher 
 */

import java.util.*;

public class FullShiftPlusHasher extends ShiftPlusHasher
{
  /**
   * takes an encryptor to use
   */
  public FullShiftPlusHasher(Encryptor ext)
  {
    super(ext);
  }

  /**
   * Takes an initializatino vector and encryptor to use
   */
  public FullShiftPlusHasher(Encryptor ext, String iv)
  {
    super(ext, iv);
  }

  /**
   * blockifies a string into a max of 16 chars per block
   */
  public ArrayList<String> blockify(String str)
  {
    ArrayList<String> blocks = new ArrayList<String>();

    int start = 0, end = start + 16;
    while(end < str.length())
    {
      blocks.add( str.substring(start, end) );
      start += 16;
      end += 16;
    }

    if(end >= str.length())
      blocks.add( str.substring(start, str.length()) );
  
    return blocks;
  }

  /**
   * Breaks text into 16 wide blocks at max
   * and encrypts and shifts them
   */ 
  public String hash(String password) throws Throwable
  {
    ArrayList<String> blocks = blockify(password);

    String nextIV = "";
    for(String block : blocks)
    {
      System.out.println("Hashing: " + block);
      
      nextIV = super.hash(block);
      super.setIV(nextIV); 
    
      System.out.println("Next IV: " + nextIV);
      System.out.println();
    }
    super.setIV("GO_NAVY_2018^mid");
    return nextIV;// is the final hash
  }

  /**
   * Test the new shift+ hasher
   */
  public static void main(String[] args) throws Throwable
  {
    FullShiftPlusHasher fsp = new FullShiftPlusHasher(new Vigenere());
    String pswd = "TheRainInSpainFallsMainlyOnThePlain";

    System.out.println(fsp.hash(pswd));
    System.out.println(fsp.hash("ThisIsExactly_16"));
    System.out.println(fsp.hash("ThisIsExactly_16_plus_some"));
    System.out.println(fsp.hash("ThisIsExactly_16"));
    System.out.println(fsp.hash("ThisIsExactly_16_plus_some"));
  }

}
