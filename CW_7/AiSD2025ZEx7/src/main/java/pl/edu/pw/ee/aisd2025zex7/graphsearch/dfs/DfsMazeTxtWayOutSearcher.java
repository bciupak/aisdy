package pl.edu.pw.ee.aisd2025zex7.graphsearch.dfs;

import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

public class DfsMazeTxtWayOutSearcher extends MazeTxtWayOutSearcher {

    @Override
    protected int findWayOutOfMaze(int[][] maze, int startX, int startY) {
        int nRows = maze.length;
        int nCols = maze[0].length;

        if (maze[startY][startX] != 0) {
            return -1;
        }

        boolean[][] visited = new boolean[nRows][nCols];

        java.util.Deque<int[]> stack = new java.util.ArrayDeque<>();

        stack.push(new int[]{startY, startX, 0});
        visited[startY][startX] = true;

        int[] dr = {-1, 1, 0, 0}; // row delta
        int[] dc = {0, 0, -1, 1}; // column delta

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int r = cur[0]; // row
            int c = cur[1]; // column
            int d = cur[2]; // depth

            boolean isBorder = (r == 0 || r == nRows - 1 || c == 0 || c == nCols - 1);
            if (isBorder && !(r == startY && c == startX)) {
                return d + 1;
            }

            for (int k = 3; k >= 0; k--) {
                int nr = r + dr[k]; //next row
                int nc = c + dc[k]; //next column

                if (nr < 0 || nr >= nRows || nc < 0 || nc >= nCols) {
                    continue;
                }

                if (visited[nr][nc]) {
                    continue;
                }

                if (maze[nr][nc] != 0) {
                    continue;
                }

                visited[nr][nc] = true;
                stack.push(new int[]{nr, nc, d + 1});
            }
        }

        return -1;
    }

}
