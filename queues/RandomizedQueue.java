import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] s;
  private int N=0;

  public RandomizedQueue(){
    s= (Item[]) new Object[1];
  }
  public boolean isEmpty(){
    return N==0;
  }
  public int size(){
    return N;
  }
  public void enqueue(Item item) {
    if(item==null){
      throw new IllegalArgumentException("can't enqueue with null");
    }
    if(N==s.length){
      resize(2*s.length);
    }
    if(s.length<1){
      resize(1);
    }
    s[N++]=item;
  }
  private void resize(int capacity){
    Item[] copy = (Item[]) new Object[capacity];
    for(int i=0; i<N; i++){
      copy[i] = s[i];
    }
    s=copy;
  }

  public Item dequeue(){
    if(N==0){
      throw new NoSuchElementException("randomized queue is empty");
    }
    int r = StdRandom.uniform(0, N);
    Item temp = s[r];
    if(r!=N-1){
      s[r]=s[N-1];
      s[N-1]=null;
    }
    N=N-1;
    resize(N);
    if(N>0 && N==s.length/4){
      resize(s.length/2);
    }
    return temp;
  }


  public Item sample(){
    if(N==0){
      throw new NoSuchElementException("randomized queue is empty");
    }
    return s[StdRandom.uniform(0,N)];
  }
  public Iterator<Item> iterator(){
    return new ReverseArrayIterator();
  }
  private class ReverseArrayIterator implements Iterator<Item>{
    private int i = N;

    public boolean hasNext(){
      return i>0;
    }
    public void remove(){
      throw new UnsupportedOperationException("UnsupportedOperationException");
    }
    public Item next(){
      if(hasNext() == false){
        throw new NoSuchElementException("No such element");
      }
      return s[--i];
    }
  }

  public static void main(String[] args){
    RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    rq.enqueue(769);
    rq.size();       // ==> 1
    rq.isEmpty();    // ==> false
    rq.dequeue();    // ==> 769
    rq.enqueue(490);
    rq.enqueue(490);
    rq.enqueue(490);
    for(Integer s : rq){
      StdOut.print(s + " ");
    }
  }
}
