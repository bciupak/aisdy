package pl.edu.pw.ee.aisd2025zex5.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileInputHandler {

    public static int readFile(String inputFilePath, String[] tokens, int[] counts, int l) throws IOException {
        String content = Files.readString(Path.of(inputFilePath));
        int unique = 0;
        for (int i = 0; i < content.length(); i += l) {
            int end = Math.min(i + l, content.length());
            String token = content.substring(i, end);
            int index = FindIndex.findIndex(tokens, unique, token);
            if (index == -1) {
                tokens[unique] = token;
                counts[unique] = 1;
                unique++;
            } else {
                counts[index]++;
            }
        }
        return unique;
    }

    public static void encodeFile(String inputFilePath, String[] tokens, String[] huffmanCodes, StringBuilder encodedData, int l) throws IOException {
        String content = Files.readString(Path.of(inputFilePath));
        for (int i = 0; i < content.length(); i += l) {
            int end = Math.min(i + l, content.length());
            String token = content.substring(i, end);
            int index = FindIndex.findIndex(tokens, tokens.length, token);
            if (index != -1) {
                encodedData.append(huffmanCodes[index]);
            }
        }
    }
}