package edu.hm.hafner.util;

/**
 * Contains useful math methods that are not part of {@link Math}.
 */
public class MathUtils {
    /**
     * Returns the maximum of the specified values. At least one value must be provided.
     *
     * @param values the values to obtain the maximum from
     * @return the maximum of the specified values
     */
    public int max(final int... values) {
        int maximum = values[0];
        for (int i = 1; i < values.length; i++) {
            maximum = Math.max(maximum, values[i]);
        }
        return maximum;
    }
}
