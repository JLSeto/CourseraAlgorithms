import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  WeightedQuickUnionUF uf;
  WeightedQuickUnionUF uf2;
  int n;
  int count;
  int vtop;
  int vbot;
  boolean[][] grid;

  private int xyTo1D(int row, int col){
    int map = n*(row-1)+col;
    return map;
  }

  public Percolation(int n) throws IllegalArgumentException{
    if(n<=0){
      throw new IllegalArgumentException("n is less than or equal to 0");
    }
    count = 0;
    this.n = n;
    uf = new WeightedQuickUnionUF(n*n+2); //add 1 for virtual bot and 1 for virtual top. (0 to (n^2-1+2))
    uf2 = new WeightedQuickUnionUF(n*n+1); //2nd Unionfind is for backwash...
    grid = new boolean[n+1][n+1]; //0 for closed. 1 for opened. 2 for full.
    vtop = 0;
    vbot = n*n+1;
  }

  public void open(int row, int col){
    if(isOpen(row, col)){
      return;
    }
    else{
      grid[row][col]=true;
      count++;
      ConnectRC(row, col);
    }
  }

  public void ConnectRC(int row, int col){
    int map = xyTo1D(row, col);

    if(row == 1){ //1 is the first row. Connect to virtual top
      uf.union(map, vtop);
      uf2.union(map, vtop);
    }

    if(row == n){
      uf.union(map, vbot);
    }

    if((row+1<=n) && isOpen(row+1, col)){ //Checks if the right box is open. Checks if it is valid.
      uf.union(map, xyTo1D(row+1,col));
      uf2.union(map, xyTo1D(row+1,col));
    }

    if((row-1>=1) &&  isOpen(row-1, col)){ //Checks if the left box is open. Checks if it is valid.
      uf.union(map, xyTo1D(row-1,col));
      uf2.union(map, xyTo1D(row-1,col));
    }

    if((col+1<=n) && isOpen(row, col+1)){ //Checks if the upper box is open. Checks if it is valid.
      uf.union(map, xyTo1D(row,col+1));
      uf2.union(map, xyTo1D(row,col+1));
    }

    if((col-1>=1) &&  isOpen(row, col-1)){ //Checks if the lower box is open. Checks if it is valid.
      uf.union(map, xyTo1D(row,col-1));
      uf2.union(map, xyTo1D(row,col-1));
    }

  }

  public boolean isOpen(int row, int col){
    return grid[row][col];
  }

  public boolean isFull(int row, int col){
    return uf2.connected(vtop,xyTo1D(row,col));
  }

  public int numberofOpenSites(){
    return count;
  }
  public boolean percolates(){
    return uf.connected(vtop,vbot);
  }


  public static void main(String[] args){
    Percolation perc = new Percolation(5);
    perc.open(1,1);

  }
}
