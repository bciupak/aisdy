package pl.edu.pw.ee.aisd2025zex5.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class QuickSortTest {

    @Test
    void givenNodeList_whenQsort_thenSortedByFrequency() {
        // given
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("a", 5));
        nodes.add(new Node("b", 1));
        nodes.add(new Node("c", 3));
        nodes.add(new Node("d", 2));

        // when
        QuickSort.qsort(nodes, 0, nodes.size() - 1);

        // then 
        for (int i = 1; i < nodes.size(); i++) {
            assertTrue(nodes.get(i - 1).frequency <= nodes.get(i).frequency,
                    "List is not sorted at index " + i);
        }
    }
}
