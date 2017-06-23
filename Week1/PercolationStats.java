/*----------------------------------------------------------------
 *  Author:        Leonardo Antonio dos Santos
 *  Written:       05/06/2017
 *  Last updated:  06/06/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats 10 20
 *  
 *  Generate percolation statistics based on 
 *  t computational experimente in n-by-n grid
 *
 *  % java PercolationStats 200 100
 *  mean                    = 0.5928432499999999
 *  stddev                  = 0.009119371693728897
 *  95% confidence interval = 0.5910558531480291, 0.5946306468519708
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private Percolation p = null;
    private int t, n;                       // inputs like private
    private double[] results = null;        // results in p*

    // perform t independent computational experiments on an n-by-n grid
    public PercolationStats(int n, int t) {

        if (n <= 0 || t <= 0)
            throw new java.lang.IllegalArgumentException();

        this.t = t;
        this.n = n;

        int i, j, k = 0;
        results = new double[t];

        for (int l = 0; l < t; l++) {
            p = new Percolation(this.n);        // inicialize vector
            do {
                i = StdRandom.uniform(n) + 1;   // generates random i
                j = StdRandom.uniform(n) + 1;   // generates random j
                if (!p.isOpen(i, j)) {          // check repeated values
                    p.open(i, j);               // opens site 
                    k++;                        // counts openings
                }
            } while (!p.percolates());          // check finish
            results[l] = k / (double) (n*n);    // calculates p*
            k = 0;
			p = null;							// release 'for' local memory
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        double mean = 0;
        for (int i = 0; i < t; i++)
            mean += results[i];
        mean = (mean / t);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double stddev = 0;
        for (int i = 0; i < t; i++)
            stddev += (results[i] - mean)*(results[i] - mean);
        stddev = Math.sqrt(stddev / (t-1));
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(t));
    }

    // test client, described below
    public static void main(String[] args) {
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(arg1, arg2);
        System.out.println("mean\t\t\t= " + ps.mean());
        System.out.println("stddev\t\t\t= " + ps.stddev());
        // double confint1 = ps.mean() - ((1.96 * ps.stddev()) / Math.sqrt(ps.t));
        // double confint2 = ;
        System.out.println("95% confidence interval\t= " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }

}

