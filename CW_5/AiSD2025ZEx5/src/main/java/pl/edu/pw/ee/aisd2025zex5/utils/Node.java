
package pl.edu.pw.ee.aisd2025zex5.utils;

public class Node {
    String token;
    int frequency;
    public Node left;
    public Node right;

    Node(String token, int frequency) {
        this.token = token;
        this.frequency = frequency;
    }
}

