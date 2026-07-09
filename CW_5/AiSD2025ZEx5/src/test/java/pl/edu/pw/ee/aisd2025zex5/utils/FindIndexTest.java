package pl.edu.pw.ee.aisd2025zex5.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FindIndexTest {

    @Test
    void givenArray_whenTokenExists_thenReturnIndex() {
        // given
        String[] tokens = new String[3];
        tokens[0] = "A";
        tokens[1] = "B";
        tokens[2] = "C";

        // when
        int idx = FindIndex.findIndex(tokens, 3, "B");

        // then
        assertEquals(1, idx);
    }

    @Test
    void givenArray_whenTokenMissing_thenReturnMinusOne() {
        // given
        String[] tokens = new String[2];
        tokens[0] = "X";
        tokens[1] = "Y";

        // when
        int idx = FindIndex.findIndex(tokens, 2, "Z");

        // then
        assertEquals(-1, idx);
    }
}
