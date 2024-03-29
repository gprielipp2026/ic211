/**
 * Author: Prielipp (m265112)
 *
 * Queue.java
 *
 * implements a queue
 *
 * public methods:
 * Queue()
 * void enqueue(Section s)
 * Section dequeue()
 * boolean empty()
 * Section at(int idx)
 * boolean contains(String)
 * int length()
 */ 
public class Queue
{

  private class Node
  {
    public Section data;
    public Node next;
    public Node(Section d, Node n) {
      data = d;
      next = n;
    }
  };


  private Node head;
  private Node tail;
  private int length;

  /**
   * constructor, makes sure head and tail are NULL upon creation
   */
  public Queue()
  {
    head = null;
    tail = null;
    length = 0;
  }

  /**
   * Attempt to go idx into the queue and
   * return the data at that node
   */
  public Section at(int idx)
  {
    if(idx > length || length == 0) 
      return null;

    Node iter = head;
    while(--idx >= 0)
    {
      iter = iter.next;
      if(iter == null)
        return null;
    }
    return iter.data;
  }


  /**
   * adds s to the back of the queue
   */
  public void enqueue(Section s) 
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
    length++;
  }

  /**
   * Return the length of the Queue
   */
  public int length()
  {
    return this.length;
  }

  /**
   * removes and returns string from the front of the queue
   */
  public Section dequeue() 
  { 
    if(length == 0) return null;
    
    Node data = head;
    head = head.next;
    if(head != null) length--;
    if(head == null)
    {
      tail = null;
    }
    return data.data;
  }

  /**
   * see if any of the Sections are equal to the string passed in
   */
  public boolean contains(String courseName)
  {
    Node tmp = head;
    while(tmp != null)
    {
      if(tmp.data.equals(courseName)) return true;
      tmp = tmp.next;
    }
    return false;
  }

  /**
   * returns true if the queue is empty
   */
  public boolean empty() 
  { 
    return head == null;
  }

}
