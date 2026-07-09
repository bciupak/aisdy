package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

public class MatrixChainOrderExtendedResult extends MatrixChainOrderResult {

    private final int[][] solutions;

    public MatrixChainOrderExtendedResult(int minMultiplyCost, int[][] solutions) {
        super(minMultiplyCost);
        this.solutions = solutions;
    }

    public String reconstructOptimalSolutions() {
        if (solutions == null) return "";

        int n = solutions.length - 1;

        if (n < 1) return "";
        
        StringBuilder sb = new StringBuilder();
        buildParenthesization(sb, 1, n);
        System.out.println("Reconstructed parenthesization: " + sb.toString());
        return sb.toString();
    }

    private void buildParenthesization(StringBuilder sb, int i, int j) {
        if (i == j) {
            sb.append('A').append(i);
            return;
        }

        sb.append('(');
        int k = 0;
        if (i < solutions.length && j < solutions[i].length) {
            k = solutions[i][j];
        }
        if (k <= 0) {
            k = (i + j) / 2;
        }
        buildParenthesization(sb, i, k);
        buildParenthesization(sb, k + 1, j);
        sb.append(')');
      
    }
    
}
