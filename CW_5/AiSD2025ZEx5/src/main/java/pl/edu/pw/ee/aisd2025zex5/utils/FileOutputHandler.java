package pl.edu.pw.ee.aisd2025zex5.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputHandler {
    public static StringBuilder processEncodedData(StringBuilder encodedData) {
        int totalBits = encodedData.length();
        totalBits += 3; // dodatkowe 3 bity do paddingu

        int remainder = (totalBits % 8);
        int extraBits = (8 - remainder) % 8;

        String extraBitsBinary = String.format("%3s", Integer.toBinaryString(extraBits)).replace(' ', '0');

        StringBuilder finalEncodedData = new StringBuilder();
        finalEncodedData.append(extraBitsBinary);
        finalEncodedData.append(encodedData);

        for (int i = 0; i < extraBits; i++) {
            finalEncodedData.append('0');
        }

        return finalEncodedData;
    }


    public static void writeFile(String outputFilePath, String[] tokens, String[] codes, int unique, int l, StringBuilder finalEncodedData) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write("l:" + l);
            bw.newLine();
            bw.write("tokens:" + unique);
            bw.newLine();
            for (int i = 0; i < unique; i++) {
                String token = tokens[i] == null ? "" : tokens[i];
                bw.write(token + "==" + codes[i]);
                bw.newLine();
            }
            bw.write("DATA");
            bw.newLine();
            bw.write(finalEncodedData.toString());
        } catch (IOException e) {
            System.out.println("Wystapil blad podczas zapisywania pliku: " + e.getMessage());
        }
    }

    public static void printToConsole(StringBuilder finalEncodedData) {
        System.out.println("Zakodowane dane:");
        System.out.println(finalEncodedData.toString());
    }



    
    public static void decodeFile(String inputFilePath, String outputFilePath) throws IOException {
        // sprawdzanie naglowka
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line = br.readLine();
            if (line == null || !line.startsWith("l:")) throw new IOException("Niepoprawny format pliku wejściowego"); 
            int l = Integer.parseInt(line.substring(2));
            line = br.readLine();
            if (line == null || !line.startsWith("tokens:")) throw new IOException("Niepoprawny format pliku wejściowego");
            int unique = Integer.parseInt(line.substring(7));

            java.util.List<String> codesList = new java.util.ArrayList<>();
            java.util.List<String> tokensList = new java.util.ArrayList<>();


            while ((line = br.readLine()) != null) {
                if (line.equals("DATA")) break;
                int sep = line.indexOf("==");
                if (sep == -1) {
                    tokensList.add("");
                    codesList.add(line == null ? "" : line);
                } else {
                    if (sep == 0) {
                     
                        tokensList.add("\n");
                    } else {
                        tokensList.add(line.substring(0, sep));
                    }
                    codesList.add(line.substring(sep + 2));
                }
            }

            if (line == null || !line.equals("DATA")) throw new IOException("Brak sekcji DATA");

            String[] codes = codesList.toArray(new String[0]);
            String[] tokens = tokensList.toArray(new String[0]);
            int uniqueRead = codes.length;

            StringBuilder bits = new StringBuilder();
            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                bits.append(dataLine);
            }

            if (bits.length() < 3) throw new IOException("Niepoprawne dane zakodowane");
            int extraBits = Integer.parseInt(bits.substring(0, 3), 2);
            String bitStream = bits.substring(3, bits.length() - extraBits);

            StringBuilder output = new StringBuilder();
            StringBuilder current = new StringBuilder();
            for (int i = 0; i < bitStream.length(); i++) {
                current.append(bitStream.charAt(i));
                String cur = current.toString();
                
                for (int j = 0; j < uniqueRead; j++) {
                    if (codes[j] != null && codes[j].equals(cur)) {
                        output.append(tokens[j]);
                        current.setLength(0);
                        break;
                    }
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
                bw.write(output.toString());
            }
        }
    }

    

}
