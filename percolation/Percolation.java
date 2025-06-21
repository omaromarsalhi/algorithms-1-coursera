import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private final int[][] grid;
    private int openSites;
    private final WeightedQuickUnionUF quickUnion;
    private final int length;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        grid = new int[n][n];
        length = n;
        openSites = 0;
        quickUnion = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    private void check(int row, int col){
        if ((row < 1 || row > length) || (col < 1 || col > length)) throw new IllegalArgumentException();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (!isOpen(row, col)) {

            grid[row-1][col-1] = 1;
            openSites++;
            if (row == 1)
                quickUnion.union(col-1, virtualTop);
            else if (row == length )
                quickUnion.union((row-1) * length + (col-1), virtualBottom);

            //raws
            if (row > 1 && isOpen(row - 1, col))
                quickUnion.union((row-1) * length + col-1, (row - 2) * length + col-1);
            if (row < length  && isOpen(row , col))
                quickUnion.union((row-1) * length + col-1, row  * length + col-1);

            //cols
            if (col > 1 && isOpen(row, col - 1))
                quickUnion.union((row-1) * length + col-1, (row-1)*length + col - 2);
            if (col < length  && isOpen((row), col+1 ))
                quickUnion.union((row-1) * length + col-1, (row-1) * length + col );
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return grid[row-1][col-1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);
        return isOpen(row, col) && quickUnion.find((row-1) * length + col-1) == quickUnion.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if( length==1 && isOpen(1,1) ) return true;
        return quickUnion.find(virtualTop) == quickUnion.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {

//        Percolation percolation = new Percolation(5);
//
//        percolation.open(0, 0);
//        percolation.open(1, 0);
//        percolation.open(2, 0);
//        percolation.open(3, 0);
//        percolation.open(4, 0);
//        System.out.println(percolation.numberOfOpenSites());
//
//        System.out.println("Number of open sites: " + percolation.numberOfOpenSites());
//        System.out.println("Does the system percolate? " + percolation.percolates());
        Percolation p = new Percolation(1);
        p.open(1, 1);
        System.out.println(p.percolates()); // Expected: true


//        Percolation p = new Percolation(3);
//        p.open(1, 1);
//        p.open(2, 1);
//        p.open(3, 1);
//        System.out.println(p.percolates()); // Expected: true

//        Percolation p = new Percolation(3);
//        p.open(1, 3);
//        System.out.println(p.isFull(1, 3)); // Expected: true (if it's connected to the top)



    }
}
