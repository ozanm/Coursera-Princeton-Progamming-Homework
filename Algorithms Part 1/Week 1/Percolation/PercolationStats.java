import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] thresholds;

    /**
     * Creates T number of N * N percolation grids and calculates the percent of the nodes that need to be
     * open in order for the grid to percolate. After the `PercolationStats` object has finished initialising,
     * the mean, standard deviation, and lower and upper confidence bounds can be returned
     *
     * @param N The size of the percolation grid
     * @param T The number of experiments to run
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Hold up, N & T have to be greater than zero.");
        }

        this.N = N;
        this.T = T;
        thresholds = new double[T];

        // Create T instances of new Percolation objects of size N.
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);

            // Keep count of how many nodes we have opened
            int openCount = 0;

            // Continue to open nodes until the grid percolates
            while (!p.percolates()) {
                openRandomNode(p);
                openCount++;
            }

            // And store the result in our array to run queries over it later. Convert to double
            thresholds[i] = (double) openCount / (N * N);
        }
    }

    // Returns the mean percentage of nodes that have to be open in order for the grid to percolate.
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // Returns the standard deviation from the percentage of nodes that have to be open in order for the grid to percolate
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // Return the lower bounds from the 95% confidence interval.
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    // Return the upper bounds from the 95% confidence interval.
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }

    // Private helper function that will open a random closed node from a given percolation grid.
    private void openRandomNode(Percolation p) {
        boolean openNode = true;
        int row = 0;
        int col = 0;

        // Repeat until we randomly find a closed node
        while (openNode) {
            // Generate a random index between 1 and N + 1 (1-based grid, remember)
            row = StdRandom.uniform(1, N + 1);
            col = StdRandom.uniform(1, N + 1);

            openNode = p.isOpen(row, col);
        }

        // If we reach here then we know that p[row, col] is an open node
        p.open(row, col);
    }

    // Tests the class. Outputs in format specified by the assignment.
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats s = new PercolationStats(N, T);

        System.out.println("mean:\t\t\t\t = " + s.mean());
        System.out.println("stddev:\t\t\t\t = " + s.stddev());
        System.out.println("95% confidence interval:\t = " + s.confidenceLo() + ", " + s.confidenceHi());
    }
}
