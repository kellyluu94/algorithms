// import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be a non-negative integer.");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("row or column is out of bound");
        }
        int site = mapTo1D(row, col);
        if (!grid[row - 1][col - 1]) {
            grid[row - 1][col - 1] = true;
        }

        if (1 <= site && site <= n) {
            uf.union(site, 0);
        }
        if (n * (n - 1) <= site && site <= n * n) {
            uf.union(site, n * n + 1);
        }

        int[][] neighbors = {
            {row - 1, col}, {row + 1, col}, {row, col - 1}, {row, col + 1}
        };
        for (int[] neighbor : neighbors) {
            int r = neighbor[0];
            int c = neighbor[1];
            if (isValid(r, c) && isOpen(r, c)) {
                int neighborSite = mapTo1D(r, c);
                uf.union(site, neighborSite);
            }
        }
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("row or column is out of bound");
        }
        return grid[row - 1][col - 1];
    }
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException("row or column is out of bound");
        }
        int site = mapTo1D(row, col);
        return uf.find(site) == uf.find(0);
    }
    
    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                if (grid[row - 1][col - 1]) {
                    count++;
                }
            }
        }
        return count;
    }
    
    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    private boolean isValid(int row, int col) {
        return 0 < row && row <= n && 0 < col && col <= n;
    }

    private int mapTo1D(int row, int col) {
        return (row - 1) * n + col;
    }
    
    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        Percolation p = new Percolation(n);

        p.open(1, 1);
        p.open(1, 2);
        p.open(1, 3);
        p.open(2, 1);
        p.open(2, 2);
        p.open(3, 2);
        p.open(3, 1);

        System.out.println("Is (1, 1) full? " + p.isOpen(1, 1));
        System.out.println("Is (1, 1) full? " + p.isFull(1, 1));
        System.out.println("Is (3, 2) full? " + p.isFull(3, 2));
        System.out.println("Does the system percolate? " + p.percolates());
    }
}
