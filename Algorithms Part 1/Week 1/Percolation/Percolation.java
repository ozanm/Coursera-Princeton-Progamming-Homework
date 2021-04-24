import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF full;
    private int N;
    private int top;
    private int bottom;
    private boolean[] openNodes;

    /**
     * Initialises an N * N WeightedQuickUnionUF object plus two extra nodes for the virtual top and virtual bottom
     * nodes. Creates an internal boolean array to keep track of whether a node is considered open or not.
     *
     * Also initialises a second N * N WeightedQuickUnionUF object plus one extra node as a second collection to check
     * for fullness and avoid the backwash issue.
     *
     * @param N The dimensions of the grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Woah, N must be greater than zero");
        }

        grid = new WeightedQuickUnionUF(N * N + 2);
        full = new WeightedQuickUnionUF(N * N + 1);

        this.N = N;

        top = getSingleArrayIdx(N, N) + 1;
        bottom = getSingleArrayIdx(N, N) + 2;

        openNodes = new boolean[N * N];
    }

    // Converts i and j coordinates into a single index.
    private int getSingleArrayIdx(int i, int j) {
        doOutOfBoundsCheck(i, j);

        return (N * (i - 1) + j) - 1;
    }

    // Checks to see if two given coordinates are valid.
    private boolean isValid(int i, int j) {
        return i > 0
                && j > 0
                && i <= N
                && j <= N;
    }

    // Throws the out of bounds check.
    private void doOutOfBoundsCheck(int i, int j) {
        if (!isValid(i, j)) {
            throw new IndexOutOfBoundsException("Boo! Values are out of bounds");
        }
    }

    // Opens that specified location
    public void open(int i, int j) {
        doOutOfBoundsCheck(i, j);

        if (isOpen(i, j)) {
            // No need to open this again as it's already open
            return;
        }

        int idx = getSingleArrayIdx(i, j);
        openNodes[idx] = true;


        // Node is in the top row. Union node in `grid` and `full` to the virtual top row.
        if (i == 1) {
            grid.union(top, idx);
            full.union(top, idx);
        }

        // Node is in the bottom row. Only union the node in `grid` to avoid backwash issue.
        if (i == N) {
            grid.union(bottom, idx);
        }

        // Union with the node above the given node if it is already open
        if (isValid(i - 1, j) && isOpen(i - 1, j)) {
            grid.union(getSingleArrayIdx(i - 1, j), idx);
            full.union(getSingleArrayIdx(i - 1, j), idx);
        }

        // Union with the node to the right of the given node if it is already open
        if (isValid(i, j + 1) && isOpen(i, j + 1)) {
            grid.union(getSingleArrayIdx(i, j + 1), idx);
            full.union(getSingleArrayIdx(i, j + 1), idx);
        }

        // Union with the node below the given node if it is already open
        if (isValid(i + 1, j) && isOpen(i + 1, j)) {
            grid.union(getSingleArrayIdx(i + 1, j), idx);
            full.union(getSingleArrayIdx(i + 1, j), idx);

        }

        // Union with the node to the left of the given node if it is already open
        if (isValid(i, j - 1) && isOpen(i, j - 1)) {
            grid.union(getSingleArrayIdx(i, j - 1), idx);
            full.union(getSingleArrayIdx(i, j - 1), idx);
        }
    }

    // Whether this node id open. This is checked against the internal `openNodes` array.
    public boolean isOpen(int i, int j) {
        doOutOfBoundsCheck(i, j);

        return openNodes[getSingleArrayIdx(i, j)];
    }

    // Checks if a given node if 'full'.
    public boolean isFull(int i, int j) {
        return full.connected(getSingleArrayIdx(i, j), top);
    }

    // Does this grid percolate? It percolates if the virtual top node connects to the virtual bottom node.
    public boolean percolates() {
        return grid.connected(top , bottom);
    }

    // Returns the number of open sites currently in the grid.
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean site : openNodes)
            count += site ? 1 : 0;
        return count;
    }
}
