package pl.edu.pw.ee.aisd2025zex1.sorters.quicksort.iterative.improvements;

import java.util.ArrayList;
import java.util.List;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;

public class QuickSortIterativeWithInSort<T extends Comparable<T>> implements SortingCmp<T> {
    
    private int insortRange = 20;
    private int subproblemCount = 0;

    public QuickSortIterativeWithInSort() {
        // default
    }

    public QuickSortIterativeWithInSort(int insortRange) {
        this.insortRange = insortRange;
    }

    public void setInsortRange(int insortRange) {
        this.insortRange = insortRange;
    }

    public int getInsortRange() {
        return insortRange;
    }
    
    @Override
    public void sort(T[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Input args (data) cannot be null!");
        }

        // reset instrumentation counters for this run
        subproblemCount = 0;

        quicksort(data);
    }
    
       
    private void insort(T[] data) {
        

        int n = data.length;
        T currentVal;
        int j;

        for (int i = 1; i < n; i++) {

            currentVal = data[i];

            for (j = i - 1; j >= 0 && data[j].compareTo(currentVal) > 0; j--) {
                data[j + 1] = data[j];
            }
            j++;

            data[j] = currentVal;
        }
    }



    private void quicksort(T[] data) {
        
        if (data.length < insortRange) {
            insort(data);
            return;
        }
        
        
    List<Integer> starts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

    int n = 1;
        int pivot;

        if (left < right) {

            subproblemCount = 0;
            while (n > 0) {
                n--;
                left = starts.remove(n);
                right = ends.remove(n);
                subproblemCount++;
                pivot = partition(data, left, right);

                if (pivot > left) {
                    starts.add(left);
                    ends.add(pivot);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
        }
    }

    public int getSubproblemCount() {
        return subproblemCount;
    }

    private int partition(T[] data, int start, int end) {
        T pivot = data[start];

        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (data[++left].compareTo(pivot) < 0) {
            }

            while (data[--right].compareTo(pivot) > 0) {
            }

            if (left < right) {
                swap(data, left, right);
            } else {
                break;
            }

        }

        return right;
    }

    private void swap(T[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            T firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }

}