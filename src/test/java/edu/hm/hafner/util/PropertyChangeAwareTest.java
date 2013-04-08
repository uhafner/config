package edu.hm.hafner.util;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.Test;

/**
 * Tests the class {@link PropertyChangeAware}.
 *
 * @author Ullrich Hafner
 */
public class PropertyChangeAwareTest {
    private static final String STRING_NEW_VALUE = "New";
    private static final String STRING_OLD_VALUE = "Old";
    private static final int INTEGER_NEW_VALUE = 1;
    private static final int INTEGER_OLD_VALUE = 0;
    private static final boolean BOOLEAN_NEW_VALUE = false;
    private static final boolean BOOLEAN_OLD_VALUE = true;
    private static final String TEST_PROPERTY = "test";
    private static final String TEST_RECEIVER_PROPERTY = "receiver";

    /**
     * Checks whether we receive an event on fire.
     */
    @Test
    public void testPropertyIsFired() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);

        PropertyChangeAware propertyChangeAware = new PropertyChangeAware();
        propertyChangeAware.addPropertyChangeListener(listener);

        fireFourEvents(propertyChangeAware);
        verify(listener, times(4)).propertyChange((PropertyChangeEvent)anyObject());
    }

    /**
     * Fires for events for property {@link #TEST_PROPERTY}.
     *
     * @param propertyChangeAware
     *            the object to fire the events for
     */
    private void fireFourEvents(final PropertyChangeAware propertyChangeAware) {
        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, BOOLEAN_OLD_VALUE, BOOLEAN_NEW_VALUE);
        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, INTEGER_OLD_VALUE, INTEGER_NEW_VALUE);
        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, STRING_OLD_VALUE, STRING_NEW_VALUE);
        propertyChangeAware.firePropertyChangeEvent(new PropertyChangeEvent(this, TEST_PROPERTY, STRING_OLD_VALUE, STRING_NEW_VALUE));
    }

    /**
     * Checks whether we don't receive an event on fire if we register for a different property.
     */
    @Test
    public void testNamedPropertyIsFiredButNotCatched() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);

        PropertyChangeAware propertyChangeAware = new PropertyChangeAware();
        propertyChangeAware.addPropertyChangeListener(TEST_RECEIVER_PROPERTY, listener);

        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, BOOLEAN_OLD_VALUE, BOOLEAN_NEW_VALUE);
        verify(listener, never()).propertyChange((PropertyChangeEvent)anyObject());
    }

    /**
     * Checks whether we don't fire a property changed event if the property has not been changed.
     */
    @Test
    public void testPropertyIsNotFiredIfNotChanged() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);

        PropertyChangeAware propertyChangeAware = new PropertyChangeAware();
        propertyChangeAware.addPropertyChangeListener(listener);

        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, BOOLEAN_OLD_VALUE, BOOLEAN_OLD_VALUE);
        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, INTEGER_OLD_VALUE, INTEGER_OLD_VALUE);
        propertyChangeAware.firePropertyChangeEvent(TEST_PROPERTY, STRING_OLD_VALUE, STRING_OLD_VALUE);
        propertyChangeAware.firePropertyChangeEvent(new PropertyChangeEvent(this, TEST_PROPERTY, STRING_OLD_VALUE, STRING_OLD_VALUE));
        verify(listener, never()).propertyChange((PropertyChangeEvent)anyObject());
    }

    /**
     * Checks whether we receive an event on fire if we register for the matching property.
     */
    @Test
    public void testNamedPropertyIsFired() {
        PropertyChangeListener removedListener = mock(PropertyChangeListener.class);
        PropertyChangeListener notRemovedListener = mock(PropertyChangeListener.class);

        PropertyChangeAware propertyChangeAware = new PropertyChangeAware();
        propertyChangeAware.addPropertyChangeListener(TEST_PROPERTY, removedListener);
        propertyChangeAware.addPropertyChangeListener(TEST_PROPERTY, notRemovedListener);

        fireFourEvents(propertyChangeAware);

        propertyChangeAware.removePropertyChangeListener(notRemovedListener); // will not be removed
        propertyChangeAware.removePropertyChangeListener(TEST_PROPERTY, removedListener);

        fireFourEvents(propertyChangeAware);

        verify(removedListener, times(4)).propertyChange((PropertyChangeEvent)anyObject());
        verify(notRemovedListener, times(8)).propertyChange((PropertyChangeEvent)anyObject());
    }
}
