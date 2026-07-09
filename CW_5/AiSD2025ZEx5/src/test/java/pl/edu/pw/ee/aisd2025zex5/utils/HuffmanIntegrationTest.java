package pl.edu.pw.ee.aisd2025zex5.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

public class HuffmanIntegrationTest {

    @Test
    void givenSimpleFile_whenEncodeThenDecode_thenOriginalRestored() throws Exception {
        // given
        String content = "aaabccddde"; // simple sample
        Path input = Files.createTempFile("huff-input", ".txt");
        Path compressed = Files.createTempFile("huff-comp", ".bin");
        Path output = Files.createTempFile("huff-out", ".txt");
        Files.writeString(input, content);

        String[] tokens = new String[65536];
        int[] counts = new int[65536];

        // when: read and build
        int unique = FileInputHandler.readFile(input.toString(), tokens, counts, 1);
        assertTrue(unique > 0);

        Node root = HuffmanTreeBuilder.buildHuffmanTree(tokens, counts, unique);
        String[] codes = new String[unique];
        HuffmanCodeGenerator.generateCodes(root, "", tokens, codes, unique);

        StringBuilder encoded = new StringBuilder();
        FileInputHandler.encodeFile(input.toString(), tokens, codes, encoded, 1);

        StringBuilder finalBits = FileOutputHandler.processEncodedData(encoded);
        FileOutputHandler.writeFile(compressed.toString(), tokens, codes, unique, 1, finalBits);

        // then: decode and compare
        FileOutputHandler.decodeFile(compressed.toString(), output.toString());
        String decoded = Files.readString(output);
        assertEquals(content, decoded);

        // cleanup
        Files.deleteIfExists(input);
        Files.deleteIfExists(compressed);
        Files.deleteIfExists(output);
    }

    @Test
    void givenEncodedBits_whenProcessEncodedData_thenPaddingResultsDivisibleBy8() {
        // given
        StringBuilder encoded = new StringBuilder("1011011");

        // when
        StringBuilder finalBits = FileOutputHandler.processEncodedData(encoded);

        // then: total length should be divisible by 8
        assertEquals(0, finalBits.length() % 8);

        // first 3 bits encode the number of extra padding bits
        int extraBits = Integer.parseInt(finalBits.substring(0, 3), 2);
        // trailing zeros count == extraBits
        int trailingZeros = 0;
        for (int i = finalBits.length() - 1; i >= 0 && finalBits.charAt(i) == '0'; i--) trailingZeros++;
        assertEquals(extraBits, trailingZeros);
    }
}
