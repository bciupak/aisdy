package pl.edu.pw.ee.aisd2025zex5.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainClassTest {

    @Test
    void givenNoArgs_whenRun_thenIllegalArgumentException() {
        // given
        String[] args = new String[]{};

        // when / then
        assertThrows(IllegalArgumentException.class, () -> AiSD2025ZEx5.run(args));
    }

    @Test
    void givenInvalidL_whenRun_thenIllegalArgumentException() {
        // given
        String[] args = new String[]{"-m","comp","-s","in","-d","out","-l","0"};

        // when / then
        assertThrows(IllegalArgumentException.class, () -> AiSD2025ZEx5.run(args));
    }

    @Test
    void givenFile_whenCompressAndDecompress_thenContentRestored() throws Exception {
        // given
        String content = "aaabccddde";
        Path input = Files.createTempFile("main-in", ".txt");
        Path compressed = Files.createTempFile("main-comp", ".bin");
        Path output = Files.createTempFile("main-out", ".txt");
        Files.writeString(input, content);

        try {
            // when: compress
            String[] compArgs = new String[]{"-m","comp","-s", input.toString(), "-d", compressed.toString(), "-l", "1"};
            AiSD2025ZEx5.run(compArgs);

            // when: decompress
            String[] decompArgs = new String[]{"-m","decomp","-s", compressed.toString(), "-d", output.toString()};
            AiSD2025ZEx5.run(decompArgs);

            // then
            String decoded = Files.readString(output);
            assertEquals(content, decoded);
        } finally {
            Files.deleteIfExists(input);
            Files.deleteIfExists(compressed);
            Files.deleteIfExists(output);
        }
    }
}
