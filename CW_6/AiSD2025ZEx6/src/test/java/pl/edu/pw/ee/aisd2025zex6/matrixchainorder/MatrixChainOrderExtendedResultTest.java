package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MatrixChainOrderExtendedResultTest {

    @Test
    public void should_Reconstruct_OPTIMAL_Parenthesization_For_CLR_example() {
        int[] matrixSizes = {30, 35, 15, 5, 10, 20, 25};
        MatrixChainOrderTopDown mc = new MatrixChainOrderTopDown();
        MatrixChainOrderExtendedResult result = (MatrixChainOrderExtendedResult) mc.findOptimalOrder(matrixSizes);
        String paren = result.reconstructOptimalSolutions();
        assertThat(paren).isEqualTo("((A1(A2A3))((A4A5)A6))");
    }

}
