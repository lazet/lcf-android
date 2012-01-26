package com.yilanwuyu.android.event.eventListener;

import com.yilanwuyu.android.event.Event;
import com.yilanwuyu.android.event.EventListener;

import android.os.Handler;

/**
 * EventListener Decorator which executes the given event listener on the ui thread, through the provided Handler.
 *
 * @author John Ericksen
 * @author Lazet
 */
public class UIThreadEventListenerDecorator implements EventListener {

    protected EventListener eventListener;
    protected Handler handler;

    public UIThreadEventListenerDecorator(EventListener eventListener, Handler handler) {
        this.eventListener = eventListener;
        this.handler = handler;
    }

    public void onEvent(Event event) {
        handler.post( new EventListenerRunnable(event, eventListener));
    }
}
