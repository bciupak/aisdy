package pl.edu.pw.ee.aisd2025zex1.sorters.mergesort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex1.services.SortingCmp;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class MergeSortStabilityTest {

    private SortingCmp<Item> sorter;

    @BeforeEach
    public void setUp() {
        sorter = new MergeSort<>();
    }

    @Test
    public void shouldSortStabelyWhen_ValuesAreRepeated() {
    // given:
        Item[] items = new Item[] {
        new Item(2, 0),
        new Item(1, 1),
        new Item(2, 2),
        new Item(1, 3),
        new Item(2, 4),
    };

    // when:
    sorter.sort(items);

    // then:
    assertThat(items).extracting(Item::getValue, Item::getOriginalIndex)
        .containsExactly(
            tuple(1, 1),
            tuple(1, 3),
            tuple(2, 0),
            tuple(2, 2),
            tuple(2, 4)
        );
    }

    @Test
    public void shouldSortStabelyWhen_AllValuesEqual() {
        // given:
        Item[] items = new Item[] {
            new Item(1, 0),
            new Item(1, 1),
            new Item(1, 2),
            new Item(1, 3),
        };

        // when:
        sorter.sort(items);

        // then:
        assertThat(items).extracting(Item::getValue, Item::getOriginalIndex)
            .containsExactly(
                tuple(1, 0),
                tuple(1, 1),
                tuple(1, 2),
                tuple(1, 3)
            );
    }

    @Test
    public void shouldSortStablyWhen_SortedWithDuplicates() {
        // given:
        Item[] items = new Item[] {
            new Item(1, 0),
            new Item(1, 1),
            new Item(2, 2),
            new Item(2, 3),
            new Item(3, 4),
        };

        // when:
        sorter.sort(items);

        // then:
        assertThat(items).extracting(Item::getValue, Item::getOriginalIndex)
            .containsExactly(
                tuple(1, 0),
                tuple(1, 1),
                tuple(2, 2),
                tuple(2, 3),
                tuple(3, 4)
            );
    }

    @Test
    public void shouldSortStablyWhen_RandomizedManyDuplicates() {
        // given:
        final int n = 200;
        java.util.Random rnd = new Random(12345);
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(rnd.nextInt(5), i);
        }
        List<Integer>[] originalOrders = new ArrayList[5];
        for (int v = 0; v < 5; v++) {
            originalOrders[v] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            originalOrders[items[i].getValue()].add(items[i].getOriginalIndex());
        }

        // when:
        sorter.sort(items);

        // then:
        for (int v = 0; v < 5; v++) {
            List<Integer> sortedOrder = new ArrayList<>();
            for (Item it : items) {
                if (it.getValue() == v) sortedOrder.add(it.getOriginalIndex());
            }
            assertThat(sortedOrder).containsExactlyElementsOf(originalOrders[v]);
        }
    

    }




    private static class Item implements Comparable<Item> {
        private final int value;
        private final int originalIndex;

        Item(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }

        public int getValue() {
            return value;
        }

        public int getOriginalIndex() {
            return originalIndex;
        }

        @Override
        public int compareTo(Item item) {
            return Integer.compare(this.value, item.value);
        }
    }
}
