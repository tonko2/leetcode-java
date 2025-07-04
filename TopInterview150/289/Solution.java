import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

    private int countLiveNeighbors(int[][] board, int y, int x, int n, int m) {
        int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
        int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};

        return IntStream.range(0, 8)
                .map(k -> {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                        return 0;
                    }
                    return board[ny][nx];
                })
                .sum();
    }

    private int getNextCellState(int[][] board, int y, int x, int n, int m) {
        int liveNeighbors = countLiveNeighbors(board, y, x, n, m);

        if (board[y][x] == 1) {
            return (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
        } else {
            return (liveNeighbors == 3) ? 1 : 0;
        }
    }

    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] b = Arrays.stream(board)
                .map(int[]::clone)
                .toArray(int[][]::new);

        IntStream.range(0, n)
                .forEach(i -> IntStream.range(0, m)
                        .forEach(j -> board[i][j] = getNextCellState(b, i, j, n, m)));
    }
}
