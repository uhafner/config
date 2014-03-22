package edu.hm.hafner.util;

import java.util.Formatter;
import java.util.List;

import javax.annotation.CheckForNull;

import com.google.common.collect.Lists;

/**
 * Provides several helper methods to validate method arguments and class
 * invariants thus supporting the design by contract concept (DBC).
 * <p>
 * Note: the static methods provided by this class use a fluent interface, i.e.,
 * in order to verify an assertion a method sequence needs to be called.
 * </p>
 * Available checks:
 * <ul>
 * <li>Boolean assertions, e.g.,
 * <code>
 *   Ensure.that(collection.contains(element)).isTrue();
 * </code>
 * </li>
 * <li>String assertions, e.g.,
 * <code>
 *   Ensure.that(string).isNotEmpty();
 * </code>
 * </li>
 * <li>Object assertions, e.g.,
 * <code>
 *   Ensure.that(element).isNotNull();
 * </code>
 * </li>
 * <li>Array assertions, e.g.,
 * <code>
 *   Ensure.that(array).isNotEmpty();
 * </code>
 * </li>
 * <li>Iterable assertions, e.g.,
 * <code>
 *   Ensure.that(collection).isNotNull();
 * </code>
 * </li>
 * </ul>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Design_by_contract"> Design by Contract (Wikipedia)</a>
 * @author Ulli Hafner
 */
public final class Ensure {
    /**
     * Returns a boolean condition.
     *
     * @param value
     *            the value to check
     * @return a boolean condition
     */
    public static BooleanCondition that(final boolean value) {
        return new BooleanCondition(value);
    }

    /**
     * Returns an object condition.
     *
     * @param value
     *            the value to check
     * @param additionalValues
     *            the additional values to check
     * @return an object condition
     */
    public static ObjectCondition that(@CheckForNull final Object value, final Object... additionalValues) {
        return new ObjectCondition(value, additionalValues);
    }

    /**
     * Returns an iterable condition.
     *
     * @param value
     *            the value to check
     * @return an iterable condition
     */
    public static IterableCondition that(@CheckForNull final Iterable<?> value) {
        return new IterableCondition(value);
    }

    /**
     * Returns an array condition.
     *
     * @param value
     *            the value to check
     * @return an array condition
     */
    public static ArrayCondition that(@CheckForNull final Object[] value) {
        return new ArrayCondition(value);
    }

    /**
     * Returns a string condition.
     *
     * @param value
     *            the value to check
     * @return a string condition
     */
    public static StringCondition that(@CheckForNull final String value) {
        return new StringCondition(value);
    }

    /**
     * Returns an exception condition.
     *
     * @param value
     *            the value to check
     * @return an exception condition
     */
    public static ExceptionCondition that(@CheckForNull final Throwable value) {
        return new ExceptionCondition(value);
    }

    /**
     * Always throws an {@link AssertionFailedException}.
     *
     * @return nothing since an {@link AssertionFailedException} is thrown
     * @param <T>
     *            return type
     */
    public static <T> T thatStatementIsNeverReached() {
        throwException("This statement should never be reached.");

        return null;
    }

    /**
     * Always throws an {@link AssertionFailedException}.
     *
     * @param explanation
     *            a {@link Formatter formatted message} explaining the assertion
     * @param args
     *            Arguments referenced by the format specifiers in the formatted
     *            explanation. If there are more arguments than format
     *            specifiers, the extra arguments are ignored. The number of
     *            arguments is variable and may be zero.
     * @return nothing since an {@link AssertionFailedException} is thrown
     * @param <T>
     *            return type
     */
    public static <T> T thatStatementIsNeverReached(final String explanation, final Object... args) {
        throwException(explanation, args);

        return null;
    }

    /**
     * Throws a {@link AssertionFailedException} with the specified detail
     * message.
     *
     * @param message
     *            a {@link Formatter formatted message} with the description of
     *            the error
     * @param args
     *            Arguments referenced by the format specifiers in the formatted
     *            message. If there are more arguments than format specifiers,
     *            the extra arguments are ignored. The number of arguments is
     *            variable and may be zero.
     * @exception AssertionFailedException
     *                always thrown
     */
    private static void throwException(final String message, final Object... args) {

        throw new AssertionFailedException(String.format(message, args));
    }

    private Ensure() {
        // prevents instantiation
    }

    /**
     * Assertions for iterables.
     */
    public static class IterableCondition extends ObjectCondition {
        private final Iterable<?> value;

        /**
         * Creates a new instance of {@link IterableCondition}.
         *
         * @param value
         *            value of the condition
         */
        public IterableCondition(@CheckForNull final Iterable<?> value) {
            super(value);

            this.value = value;
        }

        /**
         * Ensures that the given iterable is not <code>null</code> and contains
         * at least one element. Additionally, ensures that each element of the
         * iterable is not <code>null</code>.
         *
         * @throws AssertionFailedException
         *             if the iterable is empty (or <code>null</code>), or at least
         *             one iterable element is <code>null</code>.
         */
        public void isNotEmpty() {
            isNotEmpty("Iterable is empty or NULL");
        }

        /**
         * Ensures that the given iterable is not <code>null</code> and contains
         * at least one element. Additionally, ensures that each element of the
         * iterable is not <code>null</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the iterable is empty (or <code>null</code>), or at
         *             least one iterable element is <code>null</code>.
         */
        public void isNotEmpty(final String explanation, final Object... args) {
            isNotNull(explanation);

            if (value.iterator().hasNext()) {
                for (Object object : value) {
                    if (object == null) {
                        throwException(explanation, args);
                    }
                }
            }
            else {
                throwException(explanation, args);
            }
        }
    }

    /**
     * Assertions for iterables.
     */
    public static class ArrayCondition extends ObjectCondition {
        private final Object[] value;

        /**
         * Creates a new instance of {@link IterableCondition}.
         *
         * @param value
         *            value of the condition
         */
        @SuppressWarnings("PMD.ArrayIsStoredDirectly")
        @edu.umd.cs.findbugs.annotations.SuppressWarnings("EI2")
        public ArrayCondition(@CheckForNull final Object[] value) {
            super(value);

            this.value = value;
        }

        /**
         * Ensures that the given array is not <code>null</code> and contains
         * at least one element. Additionally, ensures that each element of the
         * array is not <code>null</code>.
         *
         * @throws AssertionFailedException
         *             if the array is empty (or <code>null</code>), or at least
         *             one array element is <code>null</code>.
         */
        public void isNotEmpty() {
            isNotEmpty("Array is empty or NULL");
        }

        /**
         * Ensures that the given array is not <code>null</code> and contains
         * at least one element. Additionally, ensures that each element of the
         * array is not <code>null</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the array is empty (or <code>null</code>), or at least
         *             one array element is <code>null</code>.
         */
        public void isNotEmpty(final String explanation, final Object... args) {
            isNotNull(explanation);

            if (value.length == 0) {
                throwException(explanation, args);
            }
            else {
                for (Object object : value) {
                    if (object == null) {
                        throwException(explanation, args);
                    }
                }
            }
        }
    }

    /**
     * Assertions for strings.
     */
    public static class StringCondition extends ObjectCondition {
        private final String value;

        /**
         * Creates a new instance of {@link StringCondition}.
         *
         * @param value
         *            value of the condition
         */
        public StringCondition(@CheckForNull final String value) {
            super(value);

            this.value = value;
        }

        /**
         * Ensures that the given string is not <code>null</code> and contains
         * at least one character.
         *
         * @throws AssertionFailedException
         *             if the string is empty (or <code>null</code>)
         */
        public void isNotEmpty() {
            isNotEmpty("The string is empty or NULL");
        }

        /**
         * Ensures that the given string is not <code>null</code> and contains
         * at least one character.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the string is empty (or <code>null</code>)
         */
        public void isNotEmpty(final String explanation, final Object... args) {
            isNotNull(explanation);

            if (value.length() == 0) {
                throwException(explanation, args);
            }
        }

        /**
         * Ensures that the given string is not <code>null</code> and contains
         * at least one non-whitespace character.
         *
         * @throws AssertionFailedException
         *             if the string is empty (or <code>null</code>)
         */
        public void isNotBlank() {
            isNotBlank("The string is blank");
        }

        /**
         * Ensures that the given string is not <code>null</code> and contains
         * at least one non-whitespace character.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the string is empty (or <code>null</code>)
         */
        public void isNotBlank(final String explanation, final Object... args) {
            isNotNull();

            if (isBlank()) {
                throwException(explanation, args);
            }
        }

        private boolean isBlank() {
            if (value.length() == 0) {
                return true;
            }
            for (int i = 0; i < value.length(); i++) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Assertions for objects.
     */
    public static class ObjectCondition {
        private final Object value;
        private final Object[] additionalValues;

        /**
         * Creates a new instance of {@link ObjectCondition}.
         *
         * @param value
         *            value of the condition
         */
        public ObjectCondition(@CheckForNull final Object value) {
            this(value, new Object[0]);
        }

        /**
         * Creates a new instance of {@link ObjectCondition}.
         *
         * @param value
         *            value of the condition
         * @param additionalValues
         *            additional values of the condition
         */
        @edu.umd.cs.findbugs.annotations.SuppressWarnings("EI2")
        @SuppressWarnings("PMD.ArrayIsStoredDirectly")
        public ObjectCondition(@CheckForNull final Object value, final Object[] additionalValues) {
            this.value = value;
            this.additionalValues = additionalValues;
        }

        /**
         * Ensures that the given object is not <code>null</code>.
         *
         * @throws AssertionFailedException
         *             if the object is <code>null</code>
         */
        public void isNotNull() {
            isNotNull("Object is NULL");
        }

        /**
         * Ensures that the given object is not <code>null</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the object is <code>null</code>
         */
        public void isNotNull(final String explanation, final Object... args) {
            if (value == null) {
                throwException(explanation, args);
            }
            for (Object additionalValue : additionalValues) {
                if (additionalValue == null) {
                    throwException(explanation, args);
                }
            }
        }

        /**
         * Ensures that the given object is <code>null</code>.
         *
         * @throws AssertionFailedException
         *             if the object is not <code>null</code>
         */
        public void isNull() {
            isNull("Object is not NULL");
        }

        /**
         * Ensures that the given object is <code>null</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the object is not <code>null</code>
         */
        public void isNull(final String explanation, final Object... args) {
            if (value != null) {
                throwException(explanation, args);
            }
        }

        /**
         * Ensures that the given object is an instance of one of the specified types.
         *
         * @param type
         *            the type to check the specified object for
         * @param additionalTypes
         *            the additional types to check the specified object for
         * @throws AssertionFailedException
         *             the specified object is not an instance of the given type (or
         *             <code>null</code>)
         */
        public void isInstanceOf(final Class<?> type, final Class<?>... additionalTypes) {
            isNotNull();

            List<Class<?>> types = Lists.asList(type, additionalTypes);
            for (Class<?> clazz : types) {
                if (clazz.isInstance(value)) {
                    return;
                }
            }
            throwException("Object is of wrong type. Actual: %s. Expected one of: %s", value, types);
        }

        /**
         * Ensures that the given object is an instance of the specified type.
         *
         * @param type
         *            the type to check the specified object for
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             the specified object is not an instance of the given type (or
         *             <code>null</code>)
         */
        public void isInstanceOf(final Class<?> type, final String explanation, final Object... args) {
            isNotNull(explanation);

            if (!type.isInstance(value)) {
                throwException(explanation, args);
            }

        }
    }

    /**
     * Assertions for booleans.
     */
    public static class BooleanCondition {
        /** The value of the condition. */
        private final boolean value;

        /**
         * Creates a new instance of {@link BooleanCondition}.
         *
         * @param value
         *            value of the condition
         */
        public BooleanCondition(final boolean value) {
            this.value = value;
        }

        /**
         * Ensures that the given condition is <code>false</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than
         *            format specifiers, the extra arguments are ignored. The
         *            number of arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the condition is <code>true</code>
         */
        public void isFalse(final String explanation, final Object... args) {
            if (value) {
                throwException(explanation, args);
            }
        }

        /**
         * Ensures that the given condition is <code>false</code>.
         *
         * @throws AssertionFailedException
         *             if the condition is <code>true</code>
         */
        public void isFalse() {
            isFalse("Value is not FALSE");
        }

        /**
         * Ensures that the given condition is <code>true</code>.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than format
         *            specifiers, the extra arguments are ignored. The number of
         *            arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             if the condition is <code>false</code>
         */
        public void isTrue(final String explanation, final Object... args) {
            if (!value) {
                throwException(explanation, args);
            }
        }

        /**
         * Ensures that the given condition is <code>true</code>.
         *
         * @throws AssertionFailedException
         *             if the condition is <code>false</code>
         */
        public void isTrue() {
            isTrue("Value is not TRUE");
        }
    }

    /**
     * Assertions for exceptions.
     */
    public static class ExceptionCondition {
        /** The value of the condition. */
        private final Throwable value;

        /**
         * Creates a new instance of {@link BooleanCondition}.
         *
         * @param value
         *            value of the condition
         */
        public ExceptionCondition(@CheckForNull final Throwable value) {
            this.value = value;
        }

        /**
         * Ensures that the exception is never thrown. I.e., this method will
         * always throw an {@link AssertionFailedException}.
         *
         * @param explanation
         *            a {@link Formatter formatted message} explaining the
         *            assertion
         * @param args
         *            Arguments referenced by the format specifiers in the
         *            formatted explanation. If there are more arguments than
         *            format specifiers, the extra arguments are ignored. The
         *            number of arguments is variable and may be zero.
         * @throws AssertionFailedException
         *             always thrown
         * @return nothing since an {@link AssertionFailedException} is thrown
         * @param <T>
         *            return type
         */
        public <T> T isNeverThrown(final String explanation, final Object... args) {
            throw new AssertionFailedException(String.format(explanation, args), value);
        }
    }
}
