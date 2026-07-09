package pl.edu.pw.ee.aisd2025zex1.sorters.performance;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.QuickSortIterativeWithInSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators;
import static org.assertj.core.api.Assertions.assertThat;


public class PerformanceTestQuickSortWithInSort {

	@Test
	public void optimalInsortRange() throws Exception {
		System.out.println("insortRange,ms");
	final int size = 100000;
		for (int insortRange = 10; insortRange <= 1000; insortRange += 5) {
			QuickSortIterativeWithInSort<Double> sorter = new QuickSortIterativeWithInSort<>();
			sorter.setInsortRange(insortRange);
			Double[] data = Generators.createRandomData(size);
			long t0 = System.nanoTime();
			sorter.sort(data);
			long dt = System.nanoTime() - t0;
			assertThat(data).isSorted();
			double ms = dt / 1_000_000.0;
			System.out.println(String.format("%d,%.3f", insortRange, ms));
		}
	}

}

