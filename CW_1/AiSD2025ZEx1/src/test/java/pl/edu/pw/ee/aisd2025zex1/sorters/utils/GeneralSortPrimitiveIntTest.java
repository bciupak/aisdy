package pl.edu.pw.ee.aisd2025zex1.sorters.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class GeneralSortPrimitiveIntTest {

    protected Sorting sorter;

    public GeneralSortPrimitiveIntTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        int[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (data) cannot be null!";

    assertThat(exceptionCaught)
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(message);
    }

    protected static int[] generateUniqueInts(int n, long seed) {
        Double[] rndD = Generators.createRandomData(n);
        List<Integer> indices = IntStream.range(0, n).boxed().collect(Collectors.toList());
        indices.sort((a, b) -> Double.compare(rndD[a], rndD[b]));
        int[] out = new int[n];
        for (int pos = 0; pos < n; pos++) {
            int originalPos = indices.get(pos);
            out[originalPos] = pos;
        }
        return out;
    }

    protected static int[] generateManyDuplicates(int n, int smallRange, long seed) {
        Double[] rndD = Generators.createRandomData(n);
        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = (int) (rndD[i] * smallRange);
            if (out[i] < 0) out[i] = 0;
            if (out[i] >= smallRange) out[i] = smallRange - 1;
        }
        return out;
    }

    protected static double measureMedianMillis(Sorting sorter, int[] data, int repeats) {
        long[] times = new long[repeats];
        int n = data.length;
        for (int r = 0; r < repeats; r++) {
            int[] copy = Arrays.copyOf(data, n);
            long t0 = System.nanoTime();
            sorter.sort(copy);
            times[r] = System.nanoTime() - t0;
        }
        Arrays.sort(times);
        long median = times[repeats / 2];
        return median / 1_000_000.0;
    }

    @Test
    public void should_ReturnEmptyArray_When_InputIsEmpty() {
        // given
        int[] nums = new int[0];
        // when
        sorter.sort(nums);
        // then
        assertThat(nums).isEmpty();
    }

    @Test
    public void should_CorrectlySort_When_SingleElement() {
        int[] nums = new int[] {42};
        sorter.sort(nums);
        assertThat(nums).containsExactly(42);
    }

    @Test
    public void should_CorrectlySort_When_TwoElements() {
        int[] nums = new int[] {2, 1};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_ThreeElements() {
        int[] nums = new int[] {3, 1, 2};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_TwoEqualValuesPresent() {
        int[] nums = new int[] {2, 1, 2, 3};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyFirstIsWrong_Even() {
        int[] nums = new int[] {5, 1, 2, 3, 4, 6};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyFirstIsWrong_Odd() {
        int[] nums = new int[] {5, 1, 2, 3, 4};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyLastIsWrong_Even() {
        int[] nums = new int[] {1, 2, 3, 4, 6, 5};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_OnlyLastIsWrong_Odd() {
        int[] nums = new int[] {1, 2, 3, 5, 4};
        sorter.sort(nums);
        assertThat(nums).isSorted();
    }

    @Test
    public void should_CorrectlySort_When_LargeRandomData() {
        int size = 10000;
        int[] nums = generateUniqueInts(size, 12345L);
        int[] copy = Arrays.copyOf(nums, nums.length);
        sorter.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                throw new AssertionError("Array is not sorted at index " + i);
            }
        }
        java.util.Arrays.sort(copy);
        assertThat(nums).containsExactly(copy);
    }

}
