package pl.edu.pw.ee.aisd2025zex6.lcs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTest {

    private final LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    @Test
    public void should_ThrowException_When_InputIsNull() {
        // given
        String left = null;
        String top = "abc";

        // when 
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> lcs.findLcs(left, top));

        // then
        assertThat(ex).hasMessage("Input strings cannot be null");
    }

    @Test
    public void should_ReturnEmpty_When_EitherEmpty() {
        // given
        String left = "";
        String top = "abc";

        // when
        String res1 = lcs.findLcs(left, top);
        String res2 = lcs.findLcs("abc", "");

        // then
        assertThat(res1).isEmpty();
        assertThat(res2).isEmpty();
    }

    @Test
    public void should_Handle_TabCharacter() {
        // given
        String left = "\tabc";
        String top = "\txyz";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEqualTo("\t");
    }

    @Test
    public void should_Handle_NewlineCharacter() {
        // given
        String left = "\nabc";
        String top = "x\ny";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEqualTo("\n");
    }

    @Test
    public void should_Handle_CarriageReturnCharacter() {
        // given
        String left = "\ra";
        String top = "b\r";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEqualTo("\r");
    }

    @Test
    public void should_Handle_Windows_And_Linux_Newlines() {
        // given
        String left = "line1\r\nline2";
        String top = "line1\nline2";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).contains("line2");
    }

    @Test
    public void should_ReturnContainedString_When_OneContainsAnother() {
        // given
        String left = "abcdef";
        String top = "bcd";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEqualTo("bcd");
    }

    @Test
    public void should_ReturnEmpty_When_Disjoint() {
        // given
        String left = "abc";
        String top = "def";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEmpty();
    }

    @Test
    public void should_ReturnWhole_When_Identical() {
        // given
        String left = "abcdef";
        String top = "abcdef";

        // when
        String res = lcs.findLcs(left, top);

        // then
        assertThat(res).isEqualTo("abcdef");
    }

}
