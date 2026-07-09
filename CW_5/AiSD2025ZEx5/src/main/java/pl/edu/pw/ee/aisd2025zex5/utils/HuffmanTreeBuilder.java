package pl.edu.pw.ee.aisd2025zex5.utils;


import java.util.List;
import java.util.ArrayList;

public class HuffmanTreeBuilder {

    public static Node buildHuffmanTree(String[] tokens, int[] counts, int size) {
        List<Node> nodeList = new ArrayList<>();

        // tworzenie wezlow dla kazdego tokenu
        for (int i = 0; i < size; i++) {
            nodeList.add(new Node(tokens[i], counts[i]));
        }

        // budowanie drzewa
        while (nodeList.size() > 1) {
            QuickSort.qsort(nodeList, 0, nodeList.size() - 1);

            // usuwanie dwoch wezlow o najmniejszej czestotliwosci
            Node left = nodeList.remove(0);
            Node right = nodeList.remove(0);

            // tworzenie nowego wezla z dwoch najmniejszych wezlow
            Node newNode = new Node(null, left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;

            nodeList.add(newNode);
        }

        return nodeList.get(0);
    }
}
