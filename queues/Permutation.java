import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Permutation {
  public static void main(String[] args){
    int k = new Integer(args[0]);
    RandomizedQueue<String> deq = new RandomizedQueue<String>();
    while(!StdIn.isEmpty()){
      deq.enqueue(StdIn.readString());
    }
    for(int i=0; i<k; i++){
      StdOut.println(deq.dequeue());
    }
  }
}
