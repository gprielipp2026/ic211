public class Queue
{
  public void enqueue(String s)
  {
    if (head == null) 
      head = tail = new Node(s,null);
    else 
    { 
      tail.next = new Node(s,null); 
      tail = tail.next; 
    }
  }
  public String dequeue()
  { 
    Node t = head; 
    head = head.next;
    if (head == null)
      tail = null;
    return t.data; 
  }
  public boolean empty() { return head == null; }

  public Iter iterator() { return new Iter(head); }

  protected class Iter
  {
    private Node curr;
    public Iter(Node start) { curr = start; }
    public boolean hasNext() { return curr != null; }
    public String next() 
    { 
      String s = curr.data; 
      curr = curr.next;  
      return s; 
    }
  }

  private class Node
  {
    public String data;
    public Node next;
    public Node(String d, Node n) { data = d; next = n; }
  }
  
  private Node head = null, tail = null;
}
