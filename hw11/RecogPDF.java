public class RecogPDF extends RecogASCII
{
  //                               %     P     D     F
  private static int[] header = { 0x25, 0x50, 0x44, 0x46 };
  //private static int[] header = { 0x50, 0x25, 0x46, 0x44 };
  private int ind = 0;

  /**
   * returns the name of the category this tests for
   */
  public String getName() { return "PDF"; }

  /**
   * process next byte of the file
   */
  public void feed(int nextByte)
  {
    if(getState() == 2) // 2 : unknown
    {
      if(nextByte != header[ind])
      {
        setState(0); // 0 : not match
      }
    }
    ind++;
    if(ind >= header.length && getState() != 0)
      setState(1);  // 1 : match
  }
  
  /**
   * determine if this is a PDF file
   */
  public boolean decision() { return getState() == 1; }
}
