import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDefault {

    public PercolationBFS(int n) {
        super(n);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col)) return;
        if (isFull(row, col) || !isOpen(row, col)) return;

        int[] rd = {-1, 0, 0, 1};
        int[] cd = {0, -1, 1, 0};
        Queue<int[]> queue = new LinkedList<>();
        myGrid[row][col] = FULL;
        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            for (int k = 0; k < rd.length; k++) {
                int nr = cell[0] + rd[k];
                int nc = cell[1] + cd[k];
                if (inBounds(nr, nc) && myGrid[nr][nc] == OPEN) {
                    myGrid[nr][nc] = FULL;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
}
