package edu.hm.hafner.util;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import org.junit.Test;

/**
 * Tests the class {@link Singleton}.
 *
 * @author Ulli Hafner
 */
@SuppressWarnings("nls")
public class SingletonTest {
    /**
     * Verifies the handling of valid singleton sets.
     */
    @Test
    public void testValidValues() {
        String string = "String";
        assertEquals("Wrong set element", string, Singleton.get(Sets.newHashSet(string)));

        Integer integer = 42;
        assertEquals("Wrong set element", integer, Singleton.get(Sets.newHashSet(integer)));
    }

    /**
     * Verifies the handling of invalid singleton sets.
     */
    @Test(expected = AssertionFailedException.class)
    public void testEmptySet() {
        Singleton.get(Sets.newHashSet());
    }

    /**
     * Verifies the handling of invalid singleton sets.
     */
    @Test(expected = AssertionFailedException.class)
    public void testTwoElementSet() {
        Singleton.get(Sets.newHashSet("1", "2"));
    }

    /**
     * Verifies the handling of sets with null.
     */
    @Test(expected = AssertionFailedException.class)
    public void testNullElementInSet() {
        Set<Object> set = new HashSet<Object>();
        set.add(null);
        Singleton.get(set);
    }

    /**
     * Verifies the handling of <code>null</code>.
     */
    @SuppressWarnings("ConstantConditions")
    @Test(expected = AssertionFailedException.class)
    public void testNullSet() {
        Singleton.get(null);
    }
}

