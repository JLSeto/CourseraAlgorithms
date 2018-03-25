import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node first = null;
  private Node last = null;
  private int size = 0;
  private class Node{
    Item item;
    Node next;
    Node previous;
  }
  public Deque(){// construct an empty deque

  }
  public boolean isEmpty(){// is the deque empty?
    return first == null;
  }
  public int size(){// return the number of items on the deque
    return size;
  }
  public void addFirst(Item item){// add the item to the front
    if(item == null){
      throw new IllegalArgumentException("item cannot be null!");
    }
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    if(oldfirst != null){
      oldfirst.previous = first;
    }
    if(first.next==null){
      last = first;
    }
    size++;
  }
  public void addLast(Item item){// add the item to the end
    if(item == null){
      throw new IllegalArgumentException("item cannot be null!");
    }
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.previous = oldlast;
    if(oldlast != null){
      oldlast.next = last;
    }
    if(last.previous == null){
      first = last;
    }
    size++;
  }
  public Item removeFirst(){// remove and return the item from the front
    if(isEmpty()){
      throw new NoSuchElementException("Deque is empty");
    }
    Item item = first.item;
    if(size>1){
      first = first.next;
    }
    else{
      first = null;
      last = null;
    }
    size--;
    return item;
  }
  public Item removeLast(){// remove and return the item from the end
    if(isEmpty()){
      throw new NoSuchElementException("Deque is empty");
    }
    Item item = last.item;
    if(size>1){
      last = last.previous;
      Node temp = first;
      first = last;
      first.next = null;
      first = temp;
    }
    else{
      first = null;
      last = null;
    }
    size--;
    return item;
  }
  public Iterator<Item> iterator(){// return an iterator over items in order from front to end
    return new ListIterator();
  }

  private class ListIterator implements Iterator<Item>{
    private Node current = first;
    public boolean hasNext(){return current!=null;}
    public void remove(){
      throw new UnsupportedOperationException("Not supported");
    }
    public Item next(){
      if(hasNext() != true){
        throw new NoSuchElementException("No such element");
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  public static void main(String[] args){// unit testing (optional)
    Deque<Integer> deque= new Deque<Integer>();
    deque.addFirst(1);
    deque.removeFirst();   //==> 1
    deque.addLast(3);
    deque.addLast(4);
    deque.removeFirst();   //==> 3
    deque.addFirst(6);
    deque.addFirst(7);
    deque.removeLast();    //==> 4
    for(Integer s : deque){
      StdOut.println(s);
    }
  }
}
