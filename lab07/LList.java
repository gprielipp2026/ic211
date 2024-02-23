public class LList 
{
  // Node
  private class Node {
    public Thing data;
    public Node  next;
    public Node(Thing d, Node n)
    {
      data = d;
      next = n;
    }
  }

  private Node head, tail; // default to NULL
  private int length = 0;                     

  public void add(Thing t)
  {
    length++;
    if(head == null)
    {
      head = new Node(t, null);
      tail = head;
    }
    else
    {
      tail.next = new Node(t, null);
      tail = tail.next;
    }
  }

  // Iterator
  public Iter iterator()
  {
    return new Iter();
  }

  public class Iter
  {
    private Node n = head;
    public boolean hasNext()
    {
      return n != null;
    }

    public Thing next()
    {
      Thing t = n.data;
      n = n.next;
      return t;
    }
  }

}
