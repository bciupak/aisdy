package pl.edu.pw.ee.aisd2025zex5.utils;

public class HuffmanCodeGenerator {
    // generowanie kodów dla poszczególnych tokenów
    public static void generateCodes(Node node, String code, String[] tokens, String[] huffmanCodes, int uniqueTokens) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            int index = FindIndex.findIndex(tokens, uniqueTokens, node.token);
            if (index != -1) {
                huffmanCodes[index] = code;
            }
            return;
        }

        generateCodes(node.left, code + "0", tokens, huffmanCodes, uniqueTokens);
        generateCodes(node.right, code + "1", tokens, huffmanCodes, uniqueTokens);
    }
}

