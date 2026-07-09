package pl.edu.pw.ee.aisd2025zex4;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex4.service.MapInterface;

public class RbtMapTest {

    private MapInterface<Integer, String> students;

    @BeforeEach
    public void setup() {
        students = new RbtMap<>();
    }

    @Test
    public void should_ThrowException_When_PuttingNullKey() {
        // given
        Integer studentId = null;
        String studentFullName = "Miś Uszatek";

        // when
        Throwable thrown = catchThrowable(() -> {
            students.setValue(studentId, studentFullName);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Params (key, value) cannot be null.");
    }

    @Test
    public void should_ThrowException_When_GettingNullKey() {

        // given
        Integer studentId = null;

        // when
        Throwable thrown = catchThrowable(() -> {
            students.getValue(studentId);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot get value by null key.");
    }

    @Test
    public void should_ReturnNull_When_GettingFromEmptyMap() {
        // given
        Integer studentId = 123;
        // when
        String value = students.getValue(studentId);

        // then
        assertThat(value).isNull();
    }

    @Test
    public void should_PutAndGetSingleValue() {
        // given
        Integer studentId = 1;
        String studentFullName = "prof. hab. dr. inż. Jacek Starzyński";

        // when
        students.setValue(studentId, studentFullName);

        // then
        assertThat(students.getValue(studentId)).isEqualTo(studentFullName);
    }

    @Test
    public void should_UpdateValue_When_KeyExists() {
        // given
        Integer studentId = 2;
        String initialValue = "1";
        String updatedValue = "2";

        // when
        students.setValue(studentId, initialValue);
        students.setValue(studentId, updatedValue);

        // then
        assertThat(students.getValue(studentId)).isEqualTo(updatedValue);
    }

    @Test
    public void should_ThrowException_When_PuttingNullValue_RbtMap() {
        // given
        Integer studentId = 42;

        // when
        Throwable thrown = catchThrowable(() -> students.setValue(studentId, null));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Params (key, value) cannot be null.");
    }

}
