import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {

    private final double[] numberOfOpenSites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        numberOfOpenSites = new double[trials];
        int i = 0;
        while (trials > 0) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(n)+1;
                int col = StdRandom.uniformInt(n)+1;
                p.open(row, col);
            }
            numberOfOpenSites[i] = (double) p.numberOfOpenSites() / (n * n);
            trials--;
            i++;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(numberOfOpenSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(numberOfOpenSites);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(numberOfOpenSites.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(numberOfOpenSites.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 200;       // Grid size
        int trials = 100;  // Number of experiments

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }


}
