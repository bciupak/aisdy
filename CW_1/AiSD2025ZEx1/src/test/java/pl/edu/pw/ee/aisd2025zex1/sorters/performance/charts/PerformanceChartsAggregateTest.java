package pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.ASC;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.DESC;
import static pl.edu.pw.ee.aisd2025zex1.sorters.performance.charts.utils.DataArrangeType.RAND;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createAscendingData;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createDescendingData;
import static pl.edu.pw.ee.aisd2025zex1.sorters.utils.Generators.createRandomData;
import pl.edu.pw.ee.aisd2025zex1.sorters.heapsort.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.countingsort.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.insort.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.mergesort.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.recursive.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.referencesort.*;
import pl.edu.pw.ee.aisd2025zex1.sorters.selectionsort.*;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class PerformanceChartsAggregateTest<T extends Comparable<T>> {

    private static final Logger LOG = Logger.getLogger(PerformanceChartsAggregateTest.class.getName());

    private interface SorterAdapter<U> {
        void sort(U[] arr, DataArrangeType arrangeType);
        String name();
    }

    @Test
    public void runAllAndSaveCsv() {
        int step = 1000;
        int maxSize = 20000;
        int tries = 3;
        String outFile = "ex4\\performance_charts.csv";

    List<SorterAdapter<T>> sorters = new ArrayList<>();


        // counting sort (adapter uses primitive int[] instead of T[])
        try {
            CountingSort cs = new CountingSort();
            sorters.add(new SorterAdapter<T>() {
                @Override
        
                public void sort(T[] arr, DataArrangeType arrangeType) {
                    int n = arr.length;
                    int maxVal = 10_000;
                    int[] primitiveInt = new int[n];
                    switch (arrangeType) {
                        case ASC -> {
                            for (int i = 0; i < n; i++) primitiveInt[i] = i % (maxVal + 1);
                        }
                        case RAND -> {
                            java.util.Random rnd = new java.util.Random(31337);
                            for (int i = 0; i < n; i++) primitiveInt[i] = rnd.nextInt(maxVal + 1);
                        }
                        case DESC -> {
                            for (int i = 0; i < n; i++) primitiveInt[i] = (n - 1 - i) % (maxVal + 1);
                        }
                    }
                    cs.sort(primitiveInt);
                    for (int i = 0; i < n; i++) arr[i] = (T) Double.valueOf(primitiveInt[i]);
                }

                @Override
                public String name() { return cs.getClass().getSimpleName(); }
            });
        } catch (Throwable t) {
            LOG.log(Level.SEVERE, "CountingSort not available", t);
        }

        // heapsort
 
        try {
            HeapSort<T> hs = new HeapSort<>();
            sorters.add(adapterFor(hs));
        } catch (Throwable t) { LOG.log(Level.INFO, "HeapSort not added", t); }

        // insertion sort
        try {
            InsertionSort<T> ins = new InsertionSort<>();
            sorters.add(adapterFor(ins));
        } catch (Throwable t) { LOG.log(Level.INFO, "InsertionSort not added", t); }
        
        // merge sort
        try {
            MergeSort<T> ms = new MergeSort<>();
            sorters.add(adapterFor(ms));
        } catch (Throwable t) { LOG.log(Level.INFO, "MergeSort not added", t); }

        // quicksort iterative hoare
        try {
            QuickSortIterativeHoare<T> qsih = new QuickSortIterativeHoare<>();
            sorters.add(adapterFor(qsih));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortIterativeHoare not added", t); }

        // quicksort iterative lomuto
        try {
            QuickSortIterativeLomuto<T> qsil = new QuickSortIterativeLomuto<>();
            sorters.add(adapterFor(qsil));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortIterativeLomuto not added", t); }

        // quicksort iterative hoare improved median-of-three
        try {
            QuickSortIterativeMedian3<T> qsihim3 = new QuickSortIterativeMedian3<>();
            sorters.add(adapterFor(qsihim3));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortIterativeMedian3 not added", t); }

        // quicksort iterative hoare improved Random
        try {
            QuickSortIterativeRandom<T> qsihir = new QuickSortIterativeRandom<>();
            sorters.add(adapterFor(qsihir));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortIterativeRandom not added", t); }

        // quicksort iterative hoare improved with insertion sort
        try {
            QuickSortIterativeWithInSort<T> qsihiwis = new QuickSortIterativeWithInSort<>();
            sorters.add(adapterFor(qsihiwis));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortIterativeWithInSort not added", t); }

        //quicksort recursive hoare
        try {
            QuickSortRecursiveHoare<T> qsrh = new QuickSortRecursiveHoare<>();
            sorters.add(adapterFor(qsrh));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortRecursiveHoare not added", t); }

        // quicksort recursive lomuto
        try {
            QuickSortRecursiveLomuto<T> qsrl = new QuickSortRecursiveLomuto<>();
            sorters.add(adapterFor(qsrl));
        } catch (Throwable t) { LOG.log(Level.INFO, "QuickSortRecursiveLomuto not added", t); }

        // reference sort
        try {
            ReferenceAlgSort<T> rs = new ReferenceAlgSort<>();
            sorters.add(adapterFor(rs));
        } catch (Throwable t) { LOG.log(Level.INFO, "ReferenceAlgSort not added", t); }

        try {
            SelectionSort<T> ss = new SelectionSort<>();
            sorters.add(adapterFor(ss));
        } catch (Throwable t) { LOG.log(Level.INFO, "SelectionSort not added", t); }

        if (sorters.isEmpty()) {
            LOG.severe("No sorters available to benchmark.");
            return;
        }

        DataArrangeType[] types = new DataArrangeType[]{ASC, RAND, DESC};

        try (FileWriter fw = new FileWriter(outFile, false); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("sorter,arrangement,size,median_nanos\n");

            for (SorterAdapter<T> s : sorters) {
                for (DataArrangeType type : types) {
                    for (int n = 0; n <= maxSize; n += step) {
                        long[] sampleTimes = new long[tries];
                        for (int t = 0; t < tries; t++) {
                            T[] data = (T[]) createDataByType(type, n);
                            long start = System.nanoTime();
                            try { s.sort(data, type); } catch (Throwable ex) { LOG.log(Level.SEVERE, "Error during sorting", ex); }
                            sampleTimes[t] = System.nanoTime() - start;
                        }
                        long median = median(sampleTimes);
                        bw.write(String.format("%s,%s,%d,%d\n", s.name(), type.name(), n, median));
                        bw.flush();
                    }
                }
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error writing CSV file", e);
        }
    }

    private SorterAdapter<T> adapterFor(SortingCmp<T> sorter) {
        return new SorterAdapter<T>() {
            @Override
            public void sort(T[] arr, DataArrangeType arrangeType) {
                sorter.sort(arr);
            }

            @Override
            public String name() { return sorter.getClass().getSimpleName(); }
        };
    }


    private T[] createDataByType(DataArrangeType type, int size) {
        switch (type) {
            case ASC: return (T[]) createAscendingData(size);
            case RAND: return (T[]) createRandomData(size);
            case DESC: return (T[]) createDescendingData(size);
            default: throw new IllegalArgumentException("Unknown type");
        }
    }

    private long median(long[] arr) {
        long[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }
}
