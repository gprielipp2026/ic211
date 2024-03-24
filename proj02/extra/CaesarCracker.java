/**
 * Prielipp (265112)
 *
 * CaesarCracker.java
 *
 * Takes a string and cracks it
 */

import java.util.*;
import java.io.*;

public class CaesarCracker extends Caesar implements Cracker
{
  // holds the probability of a character appearing
  private static HashMap<Character, Double> probOfChar; 

  public CaesarCracker()
  {
    super();
  }

  private static void fillMap()
  {
    probOfChar = new HashMap<Character, Double>();
    //probOfChar.put('e', .1202);
    //probOfChar.put('t', .0910);
    //probOfChar.put('a', .0812);
    //probOfChar.put('o', .0768);
    //probOfChar.put('i', .0731);
    //probOfChar.put('n', .0695);
    //probOfChar.put('s', .0628);
    //probOfChar.put('r', .0602);
    //probOfChar.put('h', .0592);
    //probOfChar.put('d', .0432);
    //probOfChar.put('l', .0398);
    //probOfChar.put('u', .0288);
    //probOfChar.put('c', .0271);
    //probOfChar.put('m', .0261);
    //probOfChar.put('f', .0230);
    //probOfChar.put('y', .0211);
    //probOfChar.put('w', .0209);
    //probOfChar.put('g', .0203);
    //probOfChar.put('p', .0182);
    //probOfChar.put('b', .0149);
    //probOfChar.put('v', .0111);
    //probOfChar.put('k', .0069);
    //probOfChar.put('x', .0017);
    //probOfChar.put('q', .0011);
    //probOfChar.put('j', .0010);
    //probOfChar.put('z', .0007);

    Scanner sc = null;
    try { sc = new Scanner(new File("frequencies.txt")); }
    catch(Exception e) { e.printStackTrace(); System.exit(1); }

    while(sc.hasNext())
    {
      String[] parts = sc.next().split(":");
      int cc = Integer.parseInt(parts[0]);
      double d = Double.parseDouble(parts[1]);
      //System.out.println("Read: " + c + " + " + d);
      if (cc >= 42 && cc <= 122)
      {
        char c = (char)cc;
        probOfChar.put(c, d);
      }
    }
    sc.close();

    // normalize (correct) the probabilities
    double sum = 0.00;
    for(char key : probOfChar.keySet())
    {
      sum += probOfChar.get(key);
    }
    for(char key : probOfChar.keySet())
    {
      probOfChar.put(key, probOfChar.get(key) / sum);
    }
  }

  /**
   * Fills out a HashMap<Character, Double> of the probs in a string
   */
  private static HashMap<Character, Double> genProbs(String str)
  {
    //final String alpha = "abcdefghijklmnopqrstuvwxyz"; // I know this isn't the whole available charSet
    HashMap<Character, Double> probs = new HashMap<Character, Double>();
    for(int i = 42; i <= 122; i++)
    {
      probs.put((char)i, 0.00);
      //System.out.println("genProbs: .put(" + ((char)i)+ ")");
    }

    int ttlChars = 0;

    // sum character occurences
    for(int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);
      //if (c >= 'A' && c <= 'Z')
      //{
      //int cc = (int)c;
      //int newcc = 'a' + (cc - 'A');
      //c = (char)newcc;
      //}

      //if(probs.containsKey(c))
      //{
      probs.put(c, probs.get(c) + 1.00);
      ttlChars++;
      //}
    }

    // normalize the probs
    for(char c : probs.keySet())
    {
      //System.out.println("probs[" + c + "] = " + (probs.get(c)/ttlChars));
      probs.put(c, probs.get(c) / ttlChars);
    }

    return probs;
  }

  /**
   * returns the percentage a HashMap<char, probs> matches probOfChar within a given
   * tolerance (let's say 1%, 0.01)
   */
  private static double looseMatch(Map<Character, Double> probs)
  {
    double prob = 0.00;
    final double tolerance = 0.051250; // 5%
    for(char key : probOfChar.keySet())
    {
      //System.out.println("Evaluating <" + key + ">");
      //double a = probOfChar.get(key);
      //System.out.println("probOfChar<" + a + ">");
      //double b = probs.get(key);
      //System.out.println("probs<" + b + ">");
      double match = probOfChar.get(key) * probs.get(key);
      //if(match < 0) match *= -1;
      
      //if( match > 0 )
      //System.out.println("match = " + match);

      //if(match <= tolerance)
      //prob += 1.00;
      prob += match;
    }
    return Math.log(prob);
    //return Math.log(prob / probOfChar.keySet().size());
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

    HashMap<Info, Double> matchProb = new HashMap<Info, Double>();
    for(Info info : arr)
    {
      // remove the <$key> (should be 3 chars long)
      String plain = info.toString().substring(0,info.toString().length() - 3);

      HashMap<Character, Double> localProbs = genProbs(plain);

      for(char c : localProbs.keySet())
        //System.out.println("localProbs[" + c + "] = " + localProbs.get(c));

      matchProb.put(info, looseMatch(localProbs));
    }

    // find the max prob (most likely match)
    // this is the part of the algorithm that would
    // most likely need the most changing
    double maxProb = -1000000000;
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

      String attempt = "";
      try { attempt = decrypt(cipher); }
      catch (Throwable t) { t.printStackTrace(); }
      Info info = new Info(attempt, new String(key));
      infos.add(info);
    }

    return CaesarCracker.filter(infos); 
  }

  public static void main(String[] args)
  {
    if(args.length != 1)
    {
      System.out.println("Usage: java CaesarCracker <ciphertext password>");
      System.exit(0);
    }

    CaesarCracker cc = new CaesarCracker();

    System.out.println(cc.crack(args[0]));
  }
}
