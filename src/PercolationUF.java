public class PercolationUF implements IPercolate {
    private boolean[][] myGrid;
    private int myOpenCount;
    private IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myOpenCount = 0;
        finder.initialize(size * size + 2);
        myFinder = finder;
        VTOP = size * size;
        VBOTTOM = size * size + 1;
    }

    private boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    private int getIndex(int row, int col) {
        return row * myGrid.length + col;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        if (myGrid[row][col]) return;

        myGrid[row][col] = true;
        myOpenCount += 1;
        int index = getIndex(row, col);

        if (row == 0) myFinder.union(index, VTOP);
        if (row == myGrid.length - 1) myFinder.union(index, VBOTTOM);

        int[] rd = {-1, 0, 0, 1};
        int[] cd = {0, -1, 1, 0};
        for (int k = 0; k < rd.length; k++) {
            int nr = row + rd[k];
            int nc = col + cd[k];
            if (inBounds(nr, nc) && myGrid[nr][nc]) {
                myFinder.union(index, getIndex(nr, nc));
            }
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myFinder.connected(getIndex(row, col), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
