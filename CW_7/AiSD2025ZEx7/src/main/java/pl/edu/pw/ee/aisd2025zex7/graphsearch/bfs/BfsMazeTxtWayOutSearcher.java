package pl.edu.pw.ee.aisd2025zex7.graphsearch.bfs;

import pl.edu.pw.ee.aisd2025zex7.graphsearch.common.MazeTxtWayOutSearcher;

public class BfsMazeTxtWayOutSearcher extends MazeTxtWayOutSearcher {

        @Override
    protected int findWayOutOfMaze(int[][] maze, int startX, int startY) {
        int nRows = maze.length;
        int nCols = maze[0].length;

        if (maze[startY][startX] != 0) {
            return -1;
        }


        boolean[][] visited = new boolean[nRows][nCols];
        int[][] dist = new int[nRows][nCols];

        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                dist[r][c] = -1;
            }
        }

        java.util.Deque<int[]> queue = new java.util.ArrayDeque<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;
        dist[startY][startX] = 0;

        int[] dr = {-1, 1, 0, 0}; // row delta
        int[] dc = {0, 0, -1, 1}; // column delta

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int r = cur[0]; // row
            int c = cur[1]; // column

            boolean isBorder = (r == 0 || r == nRows - 1 || c == 0 || c == nCols - 1);
            if (isBorder && !(r == startY && c == startX)) {
                return dist[r][c] + 1;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k]; // next row
                int nc = c + dc[k]; // next column

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
                dist[nr][nc] = dist[r][c] + 1;
                queue.add(new int[]{nr, nc});
            }
        }


        return -1;
    }
}
