package edu.hm.hafner.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Instances of this interface are capable of sending
 * {@link PropertyChangeEvent} events to attached listeners. Typically, such
 * events are sent if a bound property of this object has been changed.
 *
 * @author Ulli Hafner
 */
public interface IPropertyChangeAware {
    /**
     * Adds a {@link PropertyChangeListener} to the listener list. The listener
     * is registered for all properties. The same listener object may be added
     * more than once, and will be called as many times as it is added.
     *
     * @param listener
     *            the {@link PropertyChangeListener} to be added
     */
    void addPropertyChangeListener(final PropertyChangeListener listener);

    /**
     * Adds a {@link PropertyChangeListener} for a specific property. The
     * listener will be invoked only when a call on raisePropertyChange names
     * that specific property. The same listener object may be added more than
     * once. For each property, the listener will be invoked the number of times
     * it was added for that property.
     *
     * @param propertyName
     *            the name of the property to listen on.
     * @param listener
     *            the {@link PropertyChangeListener} to be added
     */
    void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener);

    /**
     * Removes a {@link PropertyChangeListener} from the listener list. This
     * removes a PropertyChangeListener that was registered for all properties.
     * If <code>listener</code> was added more than once to the same event
     * source, it will be notified one less time after being removed. If
     * <code>listener</code> was never added, no exception is thrown and no
     * action is taken.
     *
     * @param listener
     *            the {@link PropertyChangeListener} to be removed
     */
    void removePropertyChangeListener(final PropertyChangeListener listener);

    /**
     * Removes a {@link PropertyChangeListener} for a specific property. If
     * <code>listener</code> was added more than once to the same event source
     * for the specified property, it will be notified one less time after being
     * removed. If <code>listener</code> was never added for the specified
     * property, no exception is thrown and no action is taken.
     *
     * @param propertyName
     *            the name of the property that was listened on.
     * @param listener
     *            the {@link PropertyChangeListener} to be removed
     */
    void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener);
}
