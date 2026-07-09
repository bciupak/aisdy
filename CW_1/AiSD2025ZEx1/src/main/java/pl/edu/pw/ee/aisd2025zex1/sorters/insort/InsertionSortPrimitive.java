package pl.edu.pw.ee.aisd2025zex1.sorters.insort;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

public class InsertionSortPrimitive implements Sorting {

    @Override
    public void sort(int[] data) {
        if (isNull(data)) throw new RuntimeException("Input args (data) cannot be null!");

        int n = data.length;
        for (int i = 1; i < n; i++) {
            int current = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > current) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = current;
        }
    }
}
