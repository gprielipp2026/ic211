/**
 * Prielipp (265112)
 *
 * CaesarCracker.java
 *
 * Takes a string and cracks it
 */

import java.util.*;

public class CaesarCracker extends Caesar implements Cracker
{
  // holds the probability of a character appearing
  private static Map<char, double> probOfChar; 
  
  public CaesarCracker()
  {
    super();
  }

  private static void fillMap()
  {
    probOfChar = new Map<char, double>();
    probOfChar.put('e', .1202);
    probOfChar.put('t', .0910);
    probOfChar.put('a', .0812);
    probOfChar.put('o', .0768);
    probOfChar.put('i', .0731);
    probOfChar.put('n', .0695);
    probOfChar.put('s', .0628);
    probOfChar.put('r', .0602);
    probOfChar.put('h', .0592);
    probOfChar.put('d', .0432);
    probOfChar.put('l', .0398);
    probOfChar.put('u', .0288);
    probOfChar.put('c', .0271);
    probOfChar.put('m', .0261);
    probOfChar.put('f', .0230);
    probOfChar.put('y', .0211);
    probOfChar.put('w', .0209);
    probOfChar.put('g', .0203);
    probOfChar.put('p', .0182);
    probOfChar.put('b', .0149);
    probOfChar.put('v', .0111);
    probOfChar.put('k', .0069);
    probOfChar.put('x', .0017);
    probOfChar.put('q', .0011);
    probOfChar.put('j', .0010);
    probOfChar.put('z', .0007);

  }

  /**
   * Fills out a Map<char, double> of the probs in a string
   */
  private static Map<char, double> genProbs(String str)
  {
    final String alpha = "abcdefghijklmnopqrstuvwxyz"; // I know this isn't the whole available charSet
    Map<char, double> probs = new Map<char, double>();
    for(int i = 0; i < alpha.length(); i++)
      probs.put(alpha.get(i), 0.00);

    int ttlChars = 0;

    // sum character occurences
    for(int i = 0; i < str.length(); i++)
    {
      if(probs.containsKey(str.get(i)))
      {
        probs.put(str.get(i), probs.get(str.get(i)) + 1.00);
        ttlChars++;
      }
    }

    // normalize the probs
    for(char c : probs.keySet())
    {
      probs.put(c, probs.get(c) / ttlChars);
    }

    return probs;
  }

  /**
   * returns the percentage a Map<char, probs> matches probOfChar within a given
   * tolerance (let's say 1%, 0.01)
   */
  private static double looseMatch(Map<char, double> probs)
  {
    double prob = 0.00;
    final double tolerance = 0.01; // 1%
    for(char key : probOfChar.keySet())
    {
      double match = probOfChar.get(key) - probs.get(key);
      if(match < 0) match *= -1;

      if(match <= tolerance)
        prob += 1.00;
    }
  
    return prob / probOfChar.keySet().size();
  }

  /**
   * Selects the Info that seems the most likely correct password
   *
   */
  private static Info filter(ArrayList<Info> arr)
  {
    if(probOfChar == null)
    {
      fillMap();
    }

    Map<Info, double> matchProb = new Map<Info, double>();
    for(Info info : arr)
    {
      // remove the <$key> (should be 3 chars long)
      String plain = info.toString().substring(0,info.toString().length() - 3);

      Map<char, double> localProbs = genProbs(plain);

      matchProb.put(info, looseMatch(localProbs));
    }

    // find the max prob (most likely match)
    // this is the part of the algorithm that would
    // most likely need the most changing
    double maxProb = 0.00;
    Info   bestInfo = null;
    for(Info info : matchProb.keySet())
    {
      if(matchProb.get(info) > maxProb)
      {
        bestInfo = info;
        maxProb = matchProb.get(info);
      }
    }

    return bestInfo;
  }

  public Info crack(String cipher)
  {
    // use init(char[] key) <-- key = [testChar]; try/catch it for invalid char
    // (should be none)
    ArrayList<Info> infos = new ArrayList<Info>();
    for(int charCode = 42; charCode <= 122; charCode++)
    {
      char[] key = {(char)charCode};
      try { init(key); }
      catch (Throwable t) { t.printStackTrace(); }

      String attempt = decode(cipher);
      Info info = new Info(attempt, new String(key));
      infos.add(info);
    }

    return CaesarCracker.filter(infos); 
  }

  public static void main(String[] args)
  {

  }
}
