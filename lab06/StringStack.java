import java.util.*;
import java.io.*;
/**
 * This class provides basic Stack functionality for String objects.
 * @author Chris Brown (no, not *that* Chris Brown!)
 */
public class StringStack
{
  /**
   * push (add to front) String onto the Stack.
   * @param val The String to be pushed.
   */
  public void push(String val)
  {
    head = new Node(val, head); 
  }

  /**
   * Take first value off the Stack and return it.
   * @return String taken off the front of the Stack
   */
  public String pop()
  { 
    Node t = head; 
    head = head.next;
    return t.data; 
  }

  /**
   * This method is used to determine whether the Stack is empty.
   * @return Return true if Stack is empty, false otherwise.
   */
  public boolean empty() { return head == null; }

  private class Node
  {
    public String data;
    public Node next;
    public Node(String d, Node n) { data = d; next = n; }
  }
  
  private Node head = null;

  public static void main(String[] args)
  {
    Scanner scf = new Scanner("one fish two fish red fish blue fish");
    StringStack Q = new StringStack();
    while(scf.hasNext())
      Q.push(scf.next());
    
    while(!Q.empty())
      System.out.println(Q.pop());
  }

  /**
   * Nate Schmidt (265646) George Prielipp (265112)
   * returns an Iter
   */
  public Iter iterator()
  {
    return new Iter(head);
  }

  /**
   * Nate Schmidt (265646) George Prielipp (265112)
   * implements the Iterator concept
   */
  public class Iter {
    private Node start;

    public Iter(Node start)
    {
      this.start = start;
    }

    public boolean hasNext()
    {
      return this.start != null;
    }

    public String next()
    {
      String val = start.data;
      start = start.next;
      return val;
    }
  }
}
