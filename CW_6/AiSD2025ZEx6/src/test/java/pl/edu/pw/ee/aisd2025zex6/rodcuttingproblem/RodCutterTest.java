package pl.edu.pw.ee.aisd2025zex6.rodcuttingproblem;

import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class RodCutterTest {

    private RodCutter rodCutter;

    public RodCutterTest() {
    }

    public RodCutterTest(RodCutter rodCutter) {
        this.rodCutter = rodCutter;
    }

    @Test
    public void should_ThrowException_When_InputPricesIsNull() {
        // given
        int[] prices = null;
        int rodLength = 0;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Price array cannot be null!");
    }

    @Test
    public void should_ThrowException_When_InputRodLenghtIsNegative() {
        // given
        int[] prices = {1, 5, 8, 9};
        int rodLength = -1;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rod length cannot be negative!");
    }

    @Test
    public void should_ThrowException_When_InputRodLenghtIsBiggerThanSizeOfPriceArray() {
        // given
        int[] prices = {1, 5, 8, 9};
        int rodLength = 5;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            rodCutter.cutRod(prices, rodLength);
        });

        // then
        assertThat(exceptionCaught)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The size of the price array cannot be smaller than the length of the rod!");
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 5",
        "3, 8",
        "10, 30"
    })
    void should_ReturnCorrectValue_When_NoCuttingIsNeeded(int rodLength, int expectedResult) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterResult result = rodCutter.cutRod(prices, rodLength);
        int maxSumOfMoney = result.getMaxSumResult();

        // then
        assertThat(maxSumOfMoney).isEqualTo(expectedResult);
    }

    /*
     * Based on: Cormen, Leiserson, Rivest, Stein
     */
    @ParameterizedTest
    @CsvSource({
        "4, 10",
        "5, 13",
        "7, 18",
        "8, 22",
        "9, 25"
    })
    void should_ReturnCorrectValue_When_CuttingIsNeeded(int rodLength, int expectedResult) {
        // given
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterResult result = rodCutter.cutRod(prices, rodLength);
        int maxSumOfMoney = result.getMaxSumResult();

        // then
        assertThat(maxSumOfMoney).isEqualTo(expectedResult);
    }

 @Test
    void performanceTest() {
        final int boundary = findBoundaryFor4s(rodCutter);
        System.out.printf("Performance boundary for %s: N = %d%n", rodCutter.getClass().getSimpleName(), boundary);
    }

    private static final long TARGET_MS = 4000L;
    private static final int WARMUPS = 3;
    private static final int TRIALS = 3;
    private static final int MAX_N = 200_000;


    private int findBoundaryFor4s(RodCutter cutter) {
        Random rnd = new Random(123);
        int low = 0;
        int high = 1;


        while (high <= MAX_N) {
            int[] prices = genPrices(high, rnd);
            long t = measure(cutter, prices, high);
            System.out.printf("N=%d -> %.3fs%n", high, t / 1e3); 
            if (t >= TARGET_MS) break;
            low = high;
            high = Math.max(1, high * 2);
        }
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            int[] prices = genPrices(mid, rnd);
            long t = measure(cutter, prices, mid);
            System.out.printf("  mid=%d -> %.3fs%n", mid, t / 1e3);
            if (t >= TARGET_MS) high = mid;
            else low = mid;
        }

        return low;
    }

    private int[] genPrices(int n, Random rnd) {
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = 1 + rnd.nextInt(100);
        return p;
    }

    private long measure(RodCutter cutter, int[] prices, int n) {
        try {
            for (int i = 0; i < WARMUPS; i++) {
                try {
                    cutter.cutRod(prices, n);
                } catch (StackOverflowError soe) {
                    
                    return TARGET_MS;
                }
            }
        } catch (StackOverflowError soe) {
            return TARGET_MS;
        }

        long sum = 0;
        for (int i = 0; i < TRIALS; i++) sum += timeOne(cutter, prices, n);
        return sum / TRIALS / 1_000_000L;
    }

    private long timeOne(RodCutter cutter, int[] prices, int n) {
        long t0 = System.nanoTime();
        try {
            cutter.cutRod(prices, n);
        } catch (StackOverflowError soe) {
            return TARGET_MS * 1_000_000L;
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }





    // @Test
    // void performanceTest() {
    //     // given
    //     int rodLength = 40;
    //     int[] prices = new int[rodLength];
    //     for (int i = 0; i < rodLength; i++) {
    //         prices[i] = i + 1;
    //     }  


    // float calculateTimeOfCutting(RodCutter rodCutter, int[] prices, int rodLength) {
    //     long startTime = System.nanoTime();
    //     rodCutter.cutRod(prices, rodLength);
    //     long endTime = System.nanoTime();
    //     return (endTime - startTime) / 1_000_000f;
    // }
}
