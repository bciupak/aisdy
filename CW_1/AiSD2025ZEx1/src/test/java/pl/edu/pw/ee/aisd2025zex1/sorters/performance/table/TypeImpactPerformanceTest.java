package pl.edu.pw.ee.aisd2025zex1.sorters.performance.table;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSort;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.InsertionSortPrimitive;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.SelectionSortPrimitive;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.ReferenceAlgSortPrimitive;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import pl.edu.pw.ee.aisd2025zex1.services.Sorting;

import java.util.Random;
import java.util.Arrays;

public class TypeImpactPerformanceTest {

    private static final int[] SIZES = new int[] {
        1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288
    };

    @Test
    public void runTypeImpact() {

        System.out.println("Size,Algorithm,Type,Millis");

        for (int size : SIZES) {
            int[] base = createRandomInts(size, 12345);


            Integer[] boxed = new Integer[size];
            for (int i = 0; i < size; i++) boxed[i] = base[i];

            measureAndPrint(new InsertionSort<Integer>(), boxed, "InsertionSort", "Integer");
            measureAndPrint(new ReferenceAlgSort<Integer>(), boxed, "ReferenceSort", "Integer");
            measureAndPrint(new SelectionSort<Integer>(), boxed, "SelectionSort", "Integer");

            int[] prim = Arrays.copyOf(base, base.length);

            measureAndPrintPrim(new InsertionSortPrimitive(), prim, "InsertionSort", "int");
            measureAndPrintPrim(new ReferenceAlgSortPrimitive(), prim, "ReferenceSort", "int");
            measureAndPrintPrim(new SelectionSortPrimitive(), prim, "SelectionSort", "int");
        }
    }

    private int[] createRandomInts(int n, long seed) {
        Random rnd = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt();
        return a;
    }

    private <T extends Comparable<T>> void measureAndPrint(SortingCmp<T> sorter, T[] data, String algName, String typeName) {
        T[] copy = Arrays.copyOf(data, data.length);
        long t0 = System.nanoTime();
        sorter.sort(copy);
        long elapsed = System.nanoTime() - t0;
        System.out.println(String.format("%d,%s,%s,%.3f", data.length, algName, typeName, elapsed / 1_000_000.0));
    }

    private void measureAndPrintPrim(Sorting sorter, int[] data, String algName, String typeName) {
        int[] copy = Arrays.copyOf(data, data.length);
        long t0 = System.nanoTime();
        sorter.sort(copy);
        long elapsed = System.nanoTime() - t0;
        
        System.out.println(String.format("%d,%s,%s,%.3f", data.length, algName, typeName, elapsed / 1_000_000.0));
    }
}
