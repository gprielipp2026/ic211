public class Lab1c
{
  // numerator = (n * (n-1) * (n-2) ... (n-k+1))
  public static int getNumerator(int n, int k)
  {
    if(k == 1)
    {
      return n;
    }
    return n * getNumerator(n-1, k-1);
  }

  // denominator = k! (the factorial)
  public static int getDenominator(int k)
  {
    // there are some cases where it could break
    // I don't want that happening
    // so base case => k=0
    if(k == 0)
    {
      return 1;
    }
    return k * getDenominator(k-1);
  }

  // main function
  // calculate the odds of winning in a lottery
  public static void main(String[] args)
  {
    // track where we are in the args
    int argIndex = 0;
    
    // gets the user's name
    String name = "";
    while(args[argIndex].matches("[a-zA-Z'.]+"))
    {
      name += args[argIndex];
      argIndex++;
    } 

    // gets the two integers from the user
    int n = Integer.parseInt( args[argIndex] );
    int k = Integer.parseInt( args[argIndex + 1] );
  
    // calculate the odds of winning
    int numerator = getNumerator(n, k);
    int denominator = getDenominator(k);

    // output
    System.out.println("The two ints were " + n + " and " + k);
    System.out.println("numerator = " + numerator);
    System.out.println("denominator = " + denominator);
    System.out.println("odds = 1 in " + (numerator / denominator) + " = " + (denominator / (double)numerator));
    System.out.println("Goodbye " + name);
  }
}
