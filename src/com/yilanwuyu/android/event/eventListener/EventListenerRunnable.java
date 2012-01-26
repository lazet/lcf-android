package com.yilanwuyu.android.event.eventListener;

import com.yilanwuyu.android.event.Event;
import com.yilanwuyu.android.event.EventListener;

/**
 * Runnable implementation of the event listener on event call.
 *
* @author John Ericksen
*/
public class EventListenerRunnable implements Runnable {

    protected Event event;
    protected EventListener eventListener;

    public EventListenerRunnable(Event event, EventListener eventListener) {
        this.event = event;
        this.eventListener = eventListener;
    }

    public void run(){
        eventListener.onEvent(event);
    }
}
