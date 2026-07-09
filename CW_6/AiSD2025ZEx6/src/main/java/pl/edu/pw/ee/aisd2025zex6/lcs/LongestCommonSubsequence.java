package pl.edu.pw.ee.aisd2025zex6.lcs;

public class LongestCommonSubsequence {

    public String findLcs(String left, String top){
        if (left == null || top == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int m = left.length();
        int n = top.length();

        if (m == 0 || n == 0) return "";

        int[][] c = new int[m + 1][n + 1];
        char[][] b = new char[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (left.charAt(i - 1) == top.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 'D'; // diagonal
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 'U'; // up 
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = 'L'; // left
                }
            }
        }

        return buildLcs(b, left, m, n);
    }
    
    private String buildLcs(char[][] b, String x, int i, int j) {
        if (i == 0 || j == 0) return "";
        if (b[i][j] == 'D') {
            return buildLcs(b, x, i - 1, j - 1) + x.charAt(i - 1);
        } else if (b[i][j] == 'U') {
            return buildLcs(b, x, i - 1, j);
        } else { // 'L'
            return buildLcs(b, x, i, j - 1);
        }
    }
    
}
