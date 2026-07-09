package pl.edu.pw.ee.aisd2025zex5.utils;


import java.util.List;

public class QuickSort {

    // qsort lomuto - do sortowania listy wezlow wg czestotliwosci
    public static void qsort(List<Node> nodeList, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(nodeList, low, high);
            qsort(nodeList, low, pivotIndex - 1);
            qsort(nodeList, pivotIndex + 1, high);
        }
    }

    
    private static int partition(List<Node> nodeList, int low, int high) {
        Node pivot = nodeList.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (nodeList.get(j).frequency <= pivot.frequency) {
                i++;
                Node temp = nodeList.get(i);
                nodeList.set(i, nodeList.get(j));
                nodeList.set(j, temp);
            }
        }

        Node temp = nodeList.get(i + 1);
        nodeList.set(i + 1, nodeList.get(high));
        nodeList.set(high, temp);

        return i + 1;
    }
}

