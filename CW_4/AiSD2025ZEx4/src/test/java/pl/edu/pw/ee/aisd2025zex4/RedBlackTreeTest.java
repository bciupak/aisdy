package pl.edu.pw.ee.aisd2025zex4;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RedBlackTreeTest {

    private RedBlackTree<Integer, String> tree;

    @BeforeEach
    public void setup() {
        tree = new RedBlackTree<>();
    }

    @Test
    public void deleteMin_OnEmptyTree_DoesNothing() {
        // given:
        
        // when
        tree.deleteMin();

        // then
        assertThat(tree.get(0)).isNull();
    }

    @Test
    public void should_InsertManyAndRetrieveAll_sampleChecks() {
        // given
        final int N = 100_000;
        for (int i = 0; i < N; i++) {
            tree.put(i, "v" + i);
        }

        // when
        String first = tree.get(0);
        String second = tree.get(1);
        String last = tree.get(N - 1);
        int nonNull = 0;
        for (int i = 0; i < N; i++) {
            if (tree.get(i) != null) {
                nonNull++;
            }
        }

        // then
        assertThat(first).isEqualTo("v0");
        assertThat(second).isEqualTo("v1");
        assertThat(last).isEqualTo("v" + (N - 1));
        assertThat(nonNull).isEqualTo(N);
    }

    @Test
    public void should_DeleteAllElements_By_RepeatedDeleteMin() {
        // given
        final int N = 10_000;
        for (int i = 0; i < N; i++) {
            tree.put(i, "v" + i);
        }

        // when
        for (int i = 0; i < N; i++) {
            tree.deleteMin();
        }

        // then
        int nonNull = 0;
        for (int i = 0; i < N; i++) {
            if (tree.get(i) != null) {
                nonNull++;
            }
        }
        assertThat(nonNull).isEqualTo(0);
    }

    @Test
    public void should_MaintainValues_For_123Sequence() {
        // given
        tree.put(1, "a");
        tree.put(2, "b");
        tree.put(3, "c");

        // then 
        assertThat(tree.get(1)).isEqualTo("a");
        assertThat(tree.get(2)).isEqualTo("b");
        assertThat(tree.get(3)).isEqualTo("c");
    }

    @Test
    public void should_ThrowException_When_PuttingNullKey_DirectTreeCall() {
        // given

        // when
        Throwable thrown = catchThrowable(() -> tree.put(null, "x"));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input params (key, value) cannot be null.");
    }

    @Test
    public void should_ThrowException_When_PuttingNullValue_DirectTreeCall() {
        // given

        // when
        Throwable thrown = catchThrowable(() -> tree.put(5, null));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input params (key, value) cannot be null.");
    }

    @Test
    public void should_ThrowException_When_GettingNullKey_DirectTreeCall() {
        // given

        // when
        Throwable thrown = catchThrowable(() -> tree.get(null));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Key cannot be null.");
    }

    @Test
    public void should_MaintainValues_For_321Sequence() {
        // given
        tree.put(3, "c");
        tree.put(2, "b");
        tree.put(1, "a");

        // then 
        assertThat(tree.get(1)).isEqualTo("a");
        assertThat(tree.get(2)).isEqualTo("b");
        assertThat(tree.get(3)).isEqualTo("c");
    }

    @Test
public void deleteMin_singleNode_removesThatNode() {
    // given
   
    tree.put(10, "x"); 

    // when
    tree.deleteMin();

    // then
    assertThat(tree.get(10)).isNull();
}

}
