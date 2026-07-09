package pl.edu.pw.ee.aisd2025zex6.dodatkowe;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
public class MinCostSumTest {
    
    @Test
    public void testFindMinCostSum() {
        MinCostSum minCostSum = new MinCostSum();
        int[] fieldCost = {1, 2, 4, 6, 2};
        int result = minCostSum.findMinCostSum(fieldCost);
        assertThat(result).isEqualTo(7);
    }


    @Test
    public void testFindMinCostSum2() {
        MinCostSum minCostSum = new MinCostSum();
        int[] fieldCost = {10, 20, 30};
        int result = minCostSum.findMinCostSum(fieldCost);
        assertThat(result).isEqualTo(20);

    }


    @Test
    public void testFindMinCostSum3() {
        MinCostSum minCostSum = new MinCostSum();
        int[] fieldCost = {1, 100, 2, 3, 3, 103, 4, 5, 104, 6};
        int result = minCostSum.findMinCostSum(fieldCost);
        assertThat(result).isEqualTo(21);

    }
}