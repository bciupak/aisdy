package pl.edu.pw.ee.aisd2025zex6.dodatkowe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MaxRobberySumTest {
    

    @Test
    public void testFindMaxRobberySum() {
        MaxRobberySum maxRobberySum = new MaxRobberySum();
        int[] houseValues = {6, 7, 1, 30, 8, 2, 4};
        int result = maxRobberySum.findMaxSum(houseValues);
        assertThat(result).isEqualTo(41);
    }
}
