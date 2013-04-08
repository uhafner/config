package edu.hm.hafner.util;

/**
 * Several useful utility methods that work on {@link String} instances.
 *
 * @author Ulli Hafner
 */
public final class StringUtils {
    /**
     * Prüft, ob der übergebene String leer ist, d.h. kein Zeichen enthält.
     *
     * @param value
     *            der zu prüfende String
     * @return <code>true</code> falls der String kein Zeichen enthält oder <code>null</code> ist, <code>false</code>
     *         andernfalls.
     */
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Creates a new instance of {@link StringUtils}.
     *
     * @author Ulli Hafner
     */
    private StringUtils() {
        // prevents instantiation
    }
}
