package pl.edu.pw.ee.aisd2025zex5.main;
import pl.edu.pw.ee.aisd2025zex5.utils.Node;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import pl.edu.pw.ee.aisd2025zex5.utils.*;


public class AiSD2025ZEx5 {
    public static void main(String[] args) {
        try {
            run(args);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    static void run(String[] args) throws IOException {
        String mode = null; // comp or decomp
        String inputFilePath = null;
        String outputFilePath = null;
        int l = 1;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-m":
                    if (i + 1 < args.length) {
                        mode = args[i + 1];
                        i++;
                    } else {
                        throw new IllegalArgumentException("Brak wartości po -m");
                    }
                    break;
                case "-s":
                    if (i + 1 < args.length) {
                        inputFilePath = args[i + 1];
                        i++;
                    } else {
                        throw new IllegalArgumentException("Brak ścieżki po -s");
                    }
                    break;
                case "-d":
                    if (i + 1 < args.length) {
                        outputFilePath = args[i + 1];
                        i++;
                    } else {
                        throw new IllegalArgumentException("Brak ścieżki po -d");
                    }
                    break;
                case "-l":
                    if (i + 1 < args.length) {
                        try {
                            l = Integer.parseInt(args[i + 1]);
                        } catch (NumberFormatException ex) {
                            throw new IllegalArgumentException("Nieprawidłowa wartość -l");
                        }
                        if (l <= 0) throw new IllegalArgumentException("-l musi być >= 1");
                        i++;
                    } else {
                        throw new IllegalArgumentException("Brak wartości po -l");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Nieznany argument: " + args[i]);
            }
        }

        if (mode == null) throw new IllegalArgumentException("Proszę podać tryb -m comp|decomp");
        if (inputFilePath == null) throw new IllegalArgumentException("Proszę podać ścieżkę wejściową -s");
        if (outputFilePath == null) throw new IllegalArgumentException("Proszę podać ścieżkę wyjściową -d");

       
        Path inputPath = Path.of(inputFilePath);
        if (!Files.exists(inputPath)) {
            throw new IllegalArgumentException("Plik wejściowy nie istnieje: " + inputFilePath);
        }

        if (mode.equals("comp")) {
            compress(inputFilePath, outputFilePath, l);
            System.out.println("Kompresja zakończona: " + outputFilePath);
        } else if (mode.equals("decomp")) {
            decompress(inputFilePath, outputFilePath);
            System.out.println("Dekompresja zakończona: " + outputFilePath);
        } else {
            throw new IllegalArgumentException("Nieznany tryb: " + mode);
        }
    }

    private static void compress(String inputFilePath, String outputFilePath, int l) throws IOException {
        String[] tokens = new String[65536];
        int[] counts = new int[65536];
        int unique = FileInputHandler.readFile(inputFilePath, tokens, counts, l);

        Node root = HuffmanTreeBuilder.buildHuffmanTree(tokens, counts, unique);
        String[] codes = new String[unique];
        HuffmanCodeGenerator.generateCodes(root, "", tokens, codes, unique);

        StringBuilder encoded = new StringBuilder();
        FileInputHandler.encodeFile(inputFilePath, tokens, codes, encoded, l);

        StringBuilder finalBits = FileOutputHandler.processEncodedData(encoded);
        FileOutputHandler.writeFile(outputFilePath, tokens, codes, unique, l, finalBits);
    }

    private static void decompress(String inputFilePath, String outputFilePath) throws IOException {
        FileOutputHandler.decodeFile(inputFilePath, outputFilePath);
    }
}
