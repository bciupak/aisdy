package pl.edu.pw.ee.aisd2025zex1.sorters.countingsort;

import pl.edu.pw.ee.aisd2025zex1.sorters.utils.GeneralSortPrimitiveIntTest;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSortPrimitive;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSortPrimitive;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSortPrimitive;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;

public class CountingSortTest extends GeneralSortPrimitiveIntTest {

    public CountingSortTest() {
        super(new CountingSort());
    }
    @Test
    public void performanceCountingSort() {
        // given:
        int[] sizes = new int[] {1024,2048,4096,8192,16384,32768,65536,131072,262144,524288};
        int repeats = 3;

        System.out.println("Size; Type; MS");

        for (int size : sizes) {
            // unique 
            int[] unique = generateUniqueInts(size, 12345L);
            CountingSort csUnique = new CountingSort(Math.max(size, 10000));
            double tUnique = measureMedianMillis(csUnique, unique, repeats);
            System.out.println(String.format("%d; unique; %.3f", size, tUnique));

            // duplicates
            int smallRange = 100;
            int[] many = generateManyDuplicates(size, smallRange, 54321L);
            CountingSort csMany = new CountingSort(smallRange);
            double tMany = measureMedianMillis(csMany, many, repeats);
            System.out.println(String.format("%d,many_duplicates,%.3f", size, tMany));
        }
    }

}
