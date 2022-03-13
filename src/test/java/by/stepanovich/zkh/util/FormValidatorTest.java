package by.stepanovich.zkh.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.*;
public class FormValidatorTest {
    @ParameterizedTest
    @MethodSource("checkPasswordProvider")
    public void checkPassword(String value) {
        assertTrue(FormValidator.getInstance().checkPassword(value));
    }

    static Stream<String> checkPasswordProvider() {
        return Stream.of("Fffffffffffffff8", "Gggggggggg8", "Aababababa8", "dfhryTnnmdd9", "ddueUYEYHD85" );
    }

    @ParameterizedTest
    @MethodSource("checkPasswordNegativeProvider")
    public void checkPasswordNegative(String value) {
        assertFalse(FormValidator.getInstance().checkPassword(value));
    }

    static Stream<String> checkPasswordNegativeProvider() {
        return Stream.of("Fff8", "Gggggggggg", "Ыыыыыыыыыыыыы", "9878798797987878", "р" );
    }
}