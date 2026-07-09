package pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

public class SelectionSortPrimitive implements Sorting {

    @Override
    public void sort(int[] data) {
        if (isNull(data)) throw new RuntimeException("Input args (data) cannot be null!");

        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[min]) min = j;
            }
            if (min != i) {
                int tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;
            }
        }
    }
}
