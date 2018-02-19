import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats{

  double[] xt;
  int tries;
  int a;
  double value;

  public PercolationStats(int n, int trials) throws IllegalArgumentException{
    if(n<=0 || trials<=0){
      throw new IllegalArgumentException(" n ≤ 0 or trials ≤ 0.");
    }
    tries = trials;
    a = 0;
    xt = new double[trials];
    while(trials > 0){
      trials--;
      Percolation perc = new Percolation(n);
      while(perc.percolates()==false){
        perc.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));

      }
      xt[a] = (double)perc.numberofOpenSites()/(n*n);
      a++;
    }

  }

  public double mean(){
    return StdStats.mean(xt);
  }
  public double stddev(){
    return StdStats.stddev(xt);
  }

  public double confidenceLo(){
    return (mean()-1.96*stddev()/Math.sqrt(tries));
  }

  public double confidenceHi(){
    return (mean()+1.96*stddev()/Math.sqrt(tries));
  }

  public static void main(String[] args){
    int N = new Integer(args[0]);
    int T = new Integer(args[1]);
    PercolationStats percstats = new PercolationStats(N, T);
    StdOut.println("mean                    = " + percstats.mean());
    StdOut.println("stddev                  = " + percstats.stddev());
    StdOut.println("stddev                  = [" + percstats.confidenceLo() + ", " + percstats.confidenceHi() + "]");
  }


}
