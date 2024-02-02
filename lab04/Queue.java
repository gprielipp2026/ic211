/*
 * implements a queue
 */ 
public class Queue
{

  private class Node
  {
    public String data;
    public Node next;
    public Node(String d, Node n) {
      data = d;
      next = n;
    }
  };


  private Node head;
  private Node tail;

  /**
   * constructor, makes sure head and tail are NULL upon creation
   */
  public Queue()
  {
    head = null;
    tail = null;
  }

  /**
   * adds s to the back of the queue
   */
  public void enqueue(String s) 
  { 
    if(head == null)
    {
      head = new Node(s, null);
      tail = head;
    }
    else
    {
      Node next = new Node(s, null);
      tail.next = next;
      tail = next;
    }
  }

  /**
   * removes and returns string from the front of the queue
   */
  public String dequeue() 
  { 
    Node data = head;
    head = head.next;
    if(head == null)
    {
      tail = null;
    }
    return data.data;
  }

  /**
   * returns true if the queue is empty
   */
  public boolean empty() 
  { 
    return head == null;
  }

}
