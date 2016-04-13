package percolation;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
/**
 * Created by loster on 24.10.15.
 */
public class Percolation {
    private boolean [] open;
 //   private boolean [] full;

    private WeightedQuickUnionUF unionArray;
    private int Size;
    private int gridSize;

    public Percolation(int N)  {            // create N-by-N grid, with all sites blocked
        if (N < 0)
            throw new IllegalArgumentException("N is < 0");
        gridSize = N;
        Size = N*N + 2;
        unionArray = new WeightedQuickUnionUF(Size);
        open = new boolean[Size];
   //     full = new boolean[Size];

        open[0] = true;
        open[Size - 1] = true;
//        full[0] = true;
        for (int i = 1; i < Size; i++) {
            open[i] = false;
  //          full[i] = false;
        }
    }

    private int xyTo1D(int i, int j){      //convert row-column-coordinates to 1D-array-coordinates
        return (i-1)*gridSize + j;
    }

    private void checkBounds(int i, int j){  // throws exception
        if (i<1 || i>gridSize)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j<1 || j>gridSize)
            throw new IndexOutOfBoundsException("row index j out of bounds");
    }

    public void open(int i, int j) {       // open site (row i, column j) if it is not open already
        checkBounds(i,j);
        if (isOpen(i,j)) return;
        int index = xyTo1D(i,j);
        open[index] = true;
        if (i == 1) unionArray.union(0,index);
        if (i == gridSize) unionArray.union(index, Size-1);
        if (i > 1 && open[index - gridSize])  unionArray.union(index, index - gridSize);
        if (i < gridSize && open[index + gridSize])  unionArray.union(index, index + gridSize);
        if (j > 1 && open[index - 1])  unionArray.union(index, index - 1);
        if (j < gridSize && open[index + 1])  unionArray.union(index, index + 1);
    }

    public boolean isOpen(int i, int j) {    // is site (row i, column j) open
        checkBounds(i, j);
        int num = xyTo1D(i, j);
        return open[num];
    }


    public boolean isFull(int i, int j){     // is site (row i, column j) full?
        checkBounds(i, j);
        int num = xyTo1D(i, j);
        return unionArray.connected(num, 0);
    }

    public boolean percolates() {             // does the system percolate?
        return unionArray.connected(0, Size-1);
    }

    public static void main(String[] args)  // test client (optional)
    {
        int size = 20;
        if (args.length > 0) size = new Integer(args[0]);
        Percolation perc = new Percolation(size);

        // turn on animation mode
        StdDraw.show(0);
        PercolationVisualizer.draw(perc, size);
        StdDraw.show(100);

        double count = 0.0;                              // number of open sites
        int row;
        int column;
        while (!perc.percolates() ) {
            row = StdRandom.uniform(1, size + 1);
            column = StdRandom.uniform(1, size + 1);
            System.out.println(row + " " + column);
            if (!perc.isOpen(row, column)) {
                perc.open(row, column);
                count++;
                System.out.println("+");
                PercolationVisualizer.draw(perc, size);
                StdDraw.show(100);
            }
        }
        double Prob = count / (size * size);
        System.out.println ("N = " + size + ", P = " + Prob);
    }
}
