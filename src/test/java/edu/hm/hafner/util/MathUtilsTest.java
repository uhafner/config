package edu.hm.hafner.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the class {@link MathUtils}.
 */
public class MathUtilsTest {
    /** Verifies that max works with several elements. */
    @Test
    public void testMaxWithSeveralValues() {
        // Given
        MathUtils utils = new MathUtils();

        // When
        int actual = utils.max(1, -2, 0);

        // Then
        assertEquals("Wrong maximum", 1, actual);
    }

    /** Verifies that an exception is thrown if there are no values given. */
    @Test(expected = ArrayIndexOutOfBoundsException.class) // Then
    public void testExceptionWithNoValue() {
        // Given
        MathUtils utils = new MathUtils();

        // When
        utils.max();
    }

    /** Verifies that an exception is thrown if called with {@code null}. */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class) // Then
    public void testExceptionWithNull() {
        // Given
        MathUtils utils = new MathUtils();

        // When
        utils.max(null);
    }
}
