package pl.edu.pw.ee.aisd2025zex6.dodatkowe;

public class MaxRobberySum {
	public int findMaxSum(int[] houseVal) {
		if (houseVal == null) {
			throw new IllegalArgumentException("houseVal must not be null");
		}
		int n = houseVal.length;
		if (n == 0) {
			throw new IllegalArgumentException("houseVal length must be > 0");
		}

		if (n == 1) {
			return houseVal[0];
		}

		int[] dp = new int[n];
		dp[0] = houseVal[0];
		dp[1] = Math.max(houseVal[0], houseVal[1]);

		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + houseVal[i]);
		}

		return dp[n - 1];
	}
}
