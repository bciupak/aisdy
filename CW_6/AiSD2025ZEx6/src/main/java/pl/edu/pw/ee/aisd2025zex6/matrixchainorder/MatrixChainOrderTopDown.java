package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import java.util.Arrays;

public class MatrixChainOrderTopDown extends MatrixChainOrder {

    @Override
    public MatrixChainOrderExtendedResult findOptimalOrder(int[] matrixSizes) {
        validateInput(matrixSizes);
        int numOfMatrices = matrixSizes.length - 1;

        int[][] memo = new int[numOfMatrices + 1][numOfMatrices + 1];
        int[][] solutions = new int[numOfMatrices + 1][numOfMatrices + 1];

        for (int i = 0; i <= numOfMatrices; i++) {
            Arrays.fill(memo[i], -1);
        }

        int minMultiplyCost = computeCost(memo, solutions, matrixSizes, 1, numOfMatrices);

        return new MatrixChainOrderExtendedResult(minMultiplyCost, solutions);
    }

    private int computeCost(int[][] memo, int[][] solutions, int[] sizes, int i, int j) {
        if (i == j) {
            memo[i][j] = 0;
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int min = Integer.MAX_VALUE;
        int bestK = -1;

        for (int k = i; k < j; k++) {
            int left = computeCost(memo, solutions, sizes, i, k);
            int right = computeCost(memo, solutions, sizes, k + 1, j);
            int cost = left + right + sizes[i - 1] * sizes[k] * sizes[j];
            if (cost < min) {
                min = cost;
                bestK = k;
            }
        }

        memo[i][j] = min;
        if (bestK != -1) solutions[i][j] = bestK;
        return min;
    }
}
