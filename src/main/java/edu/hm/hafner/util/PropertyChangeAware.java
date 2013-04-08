package edu.hm.hafner.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.annotation.CheckForNull;

/**
 * Utility class that can be used as base class for beans that support bound
 * properties. You can derive from this class and re-use the listener and
 * notification functionality.
 *
 * @author Ulli Hafner
 */
public class PropertyChangeAware implements IPropertyChangeAware {
    /** Delegate to do the actual work. */
    private final PropertyChangeSupport listenerSupport = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        Ensure.that(listener).isNotNull();

        listenerSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        Ensure.that(propertyName, listener).isNotNull();

        listenerSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        Ensure.that(listener).isNotNull();

        listenerSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
        Ensure.that(propertyName, listener).isNotNull();

        listenerSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Raises an existing PropertyChangeEvent to any registered listeners. No
     * event is raised if the given event's old and new values are equal and
     * non-null.
     *
     * @param event
     *            the PropertyChangeEvent object
     */
    protected void firePropertyChangeEvent(final PropertyChangeEvent event) {
        Ensure.that(event).isNotNull();

        listenerSupport.firePropertyChange(event);
    }

    /**
     * Reports a boolean bound property update to any registered listeners. No
     * event is raised if old and new are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general
     * raisePropertyChangeEvent method that takes Object values.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    protected void firePropertyChangeEvent(final String propertyName,
            final boolean oldValue, final boolean newValue) {
        Ensure.that(propertyName).isNotNull();

        listenerSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Reports an integer bound property update to any registered listeners. No event
     * is raised if old and new are equal and non-null.
     * <p>
     * This is merely a convenience wrapper around the more general
     * raisePropertyChangeEvent method that takes Object values.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    protected void firePropertyChangeEvent(final String propertyName,
            final int oldValue, final int newValue) {
        Ensure.that(propertyName).isNotNull();

        listenerSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Reports a bound property update to any registered listeners. No event is
     * raised if old and new are equal and non-null.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param oldValue
     *            the old value of the property
     * @param newValue
     *            the new value of the property
     */
    protected void firePropertyChangeEvent(final String propertyName,
            @CheckForNull final Object oldValue, @CheckForNull final Object newValue) {
        Ensure.that(propertyName).isNotNull();

        listenerSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Reports a bound property update to any registered listeners. The raised
     * event will use <code>null</code> as reference to the old value. Use this
     * method if it is not possible to supply an old value.
     *
     * @param propertyName
     *            the programmatic name of the property that was changed
     * @param newValue
     *            the new value of the property
     */
    protected void firePropertyChangeEvent(final String propertyName, @CheckForNull final Object newValue) {
        Ensure.that(propertyName).isNotNull();

        listenerSupport.firePropertyChange(propertyName, null, newValue);
    }
}
