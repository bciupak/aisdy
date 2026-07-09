package pl.edu.pw.ee.aisd2025zex6.matrixchainorder;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.Random;
import org.junit.jupiter.api.Test;

public abstract class MatrixChainOrderTest {

    MatrixChainOrder matrixChain;
    DataUtils dataUtils;

    public MatrixChainOrderTest(MatrixChainOrder matrixChain) {
        this.matrixChain = matrixChain;
        this.dataUtils = new DataUtils();
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        int[] matrixSizes = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrder(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes cannot be null!");
    }

    @Test
    public void should_ThrowException_When_InputSizeIsLessThanTwo() {
        // given
        int[] matrixSizes = {7};

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            matrixChain.findOptimalOrder(matrixSizes);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The matrixSizes must contain at least two values!");
    }

    @Test
    public void should_ReturnCorrectResult_When_OnlyTwoMatricesAsInput() {
        // given
        int[] matrixSizes = {2, 2, 2};

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int minSumResult = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 8;
        assertThat(minSumResult).isEqualTo(expectedMinSum);
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @Test
    public void should_ReturnCorrectResult_When_CorrectSmallInput() {
        // given
        int[] matrixSizes = {30, 35, 15, 5, 10, 20, 25};

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 15125;
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }

    @Test
    public void should_ReturnCorrecetResult_When_AllSizesAreEquals() {
        // given
        int nOfMatrices = 20;
        int nOfSizes = nOfMatrices + 1;
        int sizeOfMatrix = 2;
        int[] matrixSizes = dataUtils.prepareArrayWithTheSameValue(nOfSizes, sizeOfMatrix);

        // when
        MatrixChainOrderResult result = matrixChain.findOptimalOrder(matrixSizes);
        int resultMinSum = result.getMinMultiplyCost();

        // then
        int expectedMinSum = 152;
        assertThat(resultMinSum).isEqualTo(expectedMinSum);
    }

    @Test
    void performanceTest() {
        final int boundary = findBoundaryFor4s(matrixChain);
        System.out.printf("Performance boundary for %s: N = %d%n", matrixChain.getClass().getSimpleName(), boundary);
    }

    private static final long TARGET_MS = 4000L;
    private static final int WARMUPS = 3;
    private static final int TRIALS = 3;
    private static final int MAX_N = 20_000; 

    public int findBoundaryFor4s(MatrixChainOrder mc) {
        return findBoundaryFor4s(mc, false);
    }

    public int findBoundaryFor4s(MatrixChainOrder mc, boolean linear) {
        Random rnd = new Random(123);
        int low = 0;
        int high = 1;

        while (high <= MAX_N) {
            int[] sizes = genMatrixSizes(high, rnd);
            long t = measure(mc, sizes, high);
            System.out.printf("N=%d -> %.3fs%n", high, t / 1e3);
            if (t >= TARGET_MS) break;
            low = high;
            if (linear) high = high + 1;
            else high = Math.max(1, high * 2);
        }
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            int[] sizes = genMatrixSizes(mid, rnd);
            long t = measure(mc, sizes, mid);
            System.out.printf("  mid=%d -> %.3fs%n", mid, t / 1e3);
            if (t >= TARGET_MS) high = mid;
            else low = mid;
        }

        return low;
    }

    private int[] genMatrixSizes(int n, Random rnd) {
        int[] sizes = new int[n + 1];
        for (int i = 0; i < sizes.length; i++) sizes[i] = 2 + rnd.nextInt(98);
        return sizes;
    }

    private long measure(MatrixChainOrder mc, int[] sizes, int n) {
        try {
            for (int i = 0; i < WARMUPS; i++) {
                try {
                    mc.findOptimalOrder(sizes);
                } catch (StackOverflowError soe) {
                    return TARGET_MS;
                }
            }
        } catch (StackOverflowError soe) {
            return TARGET_MS;
        }

        long sum = 0;
        for (int i = 0; i < TRIALS; i++) sum += timeOne(mc, sizes, n);
        return sum / TRIALS / 1_000_000L;
    }

    private long timeOne(MatrixChainOrder mc, int[] sizes, int n) {
        long t0 = System.nanoTime();
        try {
            mc.findOptimalOrder(sizes);
        } catch (StackOverflowError soe) {
            return TARGET_MS * 1_000_000L;
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }
}
