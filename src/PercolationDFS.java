import java.util.Stack;

public class PercolationDFS extends PercolationDefault {

    public PercolationDFS(int n) {
        super(n);
    }

    @Override
    protected void search(int row, int col) {
        if (!inBounds(row, col)) return;
        if (isFull(row, col) || !isOpen(row, col)) return;

        int[] rd = {-1, 0, 0, 1};
        int[] cd = {0, -1, 1, 0};
        Stack<int[]> stack = new Stack<>();
        myGrid[row][col] = FULL;
        stack.push(new int[]{row, col});

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            for (int k = 0; k < rd.length; k++) {
                int nr = cell[0] + rd[k];
                int nc = cell[1] + cd[k];
                if (inBounds(nr, nc) && myGrid[nr][nc] == OPEN) {
                    myGrid[nr][nc] = FULL;
                    stack.push(new int[]{nr, nc});
                }
            }
        }
    }
}
