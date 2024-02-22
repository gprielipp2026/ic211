public class HW11
{
  public static void main(String[] args)
  {
    Categorizer C = new Categorizer();
    C.add(new RecogASCII());
    C.add(new RecogJPG());
    C.add(new RecogPDF());
    C.printCategories(System.in);
  }
}
