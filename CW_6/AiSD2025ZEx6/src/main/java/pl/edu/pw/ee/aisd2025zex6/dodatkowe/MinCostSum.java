package pl.edu.pw.ee.aisd2025zex6.dodatkowe;

public class MinCostSum {


	public int findMinCostSum(int[] fieldCost) {
		if (fieldCost == null) {
			throw new IllegalArgumentException("fieldCost must not be null");
		}
		int n = fieldCost.length;
		if (n < 2) {
			throw new IllegalArgumentException("fieldCost length must be at least 2");
		}

		
		int[] dp = new int[n];

		dp[0] = fieldCost[0];
		dp[1] = fieldCost[1];

	
		for (int i = 2; i < n; i++) {
			dp[i] = fieldCost[i] + Math.min(dp[i - 1], dp[i - 2]);
		}

		return Math.min(dp[n - 1], dp[n - 2]);
	}
}

