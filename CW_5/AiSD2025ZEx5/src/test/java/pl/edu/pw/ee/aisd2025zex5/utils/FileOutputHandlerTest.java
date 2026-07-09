package pl.edu.pw.ee.aisd2025zex5.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FileOutputHandlerTest {

    @Test
    void givenDirectoryAsOutput_whenWriteFile_thenIOExceptionHandledAndMessagePrinted() throws Exception {
        // given
        Path tempDir = Files.createTempDirectory("outdir");
        String[] tokens = new String[]{"a","b"};
        String[] codes = new String[]{"0","1"};
        int unique = 2;
        int l = 1;
        StringBuilder finalBits = new StringBuilder("1010");

  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));

        try {
            // when: 
            FileOutputHandler.writeFile(tempDir.toString(), tokens, codes, unique, l, finalBits);
        } finally {
            // restore stdout
            System.setOut(oldOut);
        }

        // then:
        String printed = baos.toString();
        assertTrue(printed.contains("Wystapil blad podczas zapisywania pliku"),
                "Expected error message to be printed when write fails");

        // cleanup
        Files.deleteIfExists(tempDir);
    }
}
