import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
    private double[] result;
    private int size;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        result = new double[trials];
        size = n;
        for (int i = 0; i< this.result.length; i++) {
            Percolation percolation = new Percolation(this.size);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(this.size) + 1;
                int col = StdRandom.uniform(this.size) + 1;
                percolation.open(row, col);
            }
            this.result[i] = percolation.numberOfOpenSites() / (double) (this.size * this.size);  
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / (Math.sqrt(result.length)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / (Math.sqrt(result.length)));
    }

   // test client (see below)
   public static void main(String[] args) {
       PercolationStats stat = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.out.println("mean                    = " + stat.mean());
        System.out.println("stddev                  = " + stat.stddev());
        System.out.println("95% confidence interval = [" + stat.confidenceLo() + ", " + stat.confidenceHi()+"]");   

   }

}