package org.lcf.android.event;


/**
 * Interface for registering functionality with the EventManager.
 *
 * @author Mike Burton
 * @author Lazet
 *
 */
public interface EventListener {

    /**
     * Method called when event is triggered.
     * 
     * @param event fired
     */
    public void onEvent(Event event);
}
