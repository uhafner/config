package edu.hm.hafner.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thrown to indicate that a contract assertion check has been failed.
 *
 * @author Ulli Hafner
 */
public final class AssertionFailedException extends RuntimeException {
    private static final long serialVersionUID = -7033759120346380864L;

    /**
     * Constructs an {@link AssertionFailedException} with the specified
     * detail message.
     *
     * @param message
     *            the detail error message.
     */
    AssertionFailedException(final String message) {
        super(message);

        log(this);
    }

    /**
     * Constructs an {@link AssertionFailedException} with the specified
     * detail message and cause.
     *
     * @param message
     *            the detail error message.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    AssertionFailedException(final String message, final Throwable cause) {
        super(message, cause);

        log(cause);
    }

    private static void log(final Throwable exception) {
        LOGGER.log(Level.WARNING, "Assertion failed.", exception);
    }

    private static final Logger LOGGER = Logger.getLogger(AssertionFailedException.class.getName());
}
