import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static int trials;
    private static double[] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <=0 ) {
            throw new IllegalArgumentException("Invalid input. Both n and trials must be greater than 0.");
        }
        PercolationStats.trials = trials;
        PercolationStats.fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                p.open(row, col);
            }
            double fraction = (double) p.numberOfOpenSites() / (n * n);
            fractions[i] = fraction;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(trials);
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(trials);
    }
    
    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        double elapsedTime = stopwatch.elapsedTime();

        // System.out.println("n = " + n + ", T = " + trials);
        System.out.println("Mean = " + stats.mean());
        System.out.println("Stddev = " + stats.stddev());
        System.out.println("95% Confidence Interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
        // System.out.println("Elapsed Time: " + elapsedTime + " seconds");
    }
}