package org.lcf.android.event.eventListener;

import org.lcf.android.event.Event;
import org.lcf.android.event.EventListener;

import android.os.Handler;

/**
 * Event Listener Decorator class.  This decorator executes the event listener through the SafeAsyncTask functionality.
 *
 * @author John Ericksen
 * @author Lazet
 */
public class AsynchronousEventListenerDecorator implements EventListener{

    protected EventListener eventListener;
    protected Handler handler;

    public AsynchronousEventListenerDecorator(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public AsynchronousEventListenerDecorator(Handler handler, EventListener eventListener) {
        this.handler = handler;
        this.eventListener = eventListener;
    }

    public void onEvent(Event event) {
        new RunnableAsyncTaskAdaptor(handler, new EventListenerRunnable(event, eventListener)).execute();
    }
}
