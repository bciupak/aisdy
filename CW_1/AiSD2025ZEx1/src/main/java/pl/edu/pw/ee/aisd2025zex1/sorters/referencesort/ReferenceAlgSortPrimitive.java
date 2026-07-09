package pl.edu.pw.ee.aisd2025zex1.sorters.referencesort;

import java.util.Arrays;
import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

public class ReferenceAlgSortPrimitive implements Sorting {

    @Override
    public void sort(int[] data) {
        if (isNull(data)) throw new RuntimeException("Input args (data) cannot be null!");
        Arrays.sort(data);
    }
}
