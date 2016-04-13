package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import static java.lang.Math.*;

/**
 * Created by loster on 24.10.15.
 */
public class PercolationStats {

    private double prob [];
    private int N;
    private int T;
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T) {    // perform T independent experiments on an N-by-N grid
        if (N < 0)
            throw new IllegalArgumentException("N is < 0");
        if (T < 0)
            throw new IllegalArgumentException("T is < 0");
        prob = new double [T];
        this.N = N;
        this.T = T;
        for (int i=0; i< T; i++) {
            Percolation perc = new Percolation(N);
            double count = 0.0;                              // number of open sites
            int row;
            int column;
            while (!perc.percolates()) {
                row = StdRandom.uniform(1, N + 1);
                column = StdRandom.uniform(1, N + 1);
                if (!perc.isOpen(row, column)) {
                    perc.open(row, column);
                    count++;
                }
            }
            prob[i] = count / (N * N);
 //           System.out.println("prob[" + i + "] = " + prob[i]);
        }
    }

    public double mean()                      // sample mean of percolation threshold
    {
        mean = StdStats.mean(prob);
        return mean;
    }

    public double stddev()                    // sample standard deviation of percolation threshold
    {
        stddev = StdStats.stddev(prob);
        if (T == 1) stddev = Double.NaN;
        return stddev;
    }

    public double confidenceLo()              // low  endpoint of 95% confidence interval
    {
        return (mean - (1.96 * sqrt(stddev))/sqrt(T));
    }
    public double confidenceHi()              // high endpoint of 95% confidence interval
    {
        return (mean + (1.96 * sqrt(stddev))/sqrt(T));
    }
    public static void main(String[] args)    // test client (described below)
    {
        int N = 20;
        int T = 10;
        if (args.length > 0)
            N = new Integer(args[0]);
        if (args.length > 1)
            T = new Integer(args[1]);
        PercolationStats percStats = new PercolationStats(N,T);
        System.out.println("N = " + N + ", T = " + T);
        System.out.println("mean = " + percStats.mean());
        System.out.println("stddev = " + percStats.stddev());

        double coLo = percStats.confidenceLo();
        double coHi = percStats.confidenceHi();
        System.out.println("95% confidence interval = " + coLo + "  " + coHi);

    }
}
