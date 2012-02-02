package org.lcf.android.event.eventListener.factory;

import org.lcf.android.event.EventListener;
import org.lcf.android.event.EventThread;
import org.lcf.android.event.eventListener.AsynchronousEventListenerDecorator;
import org.lcf.android.event.eventListener.UIThreadEventListenerDecorator;

import android.os.Handler;
import android.os.Looper;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author John Ericksen
 * @author Lazet
 */
public class EventListenerThreadingDecorator {

    public  EventListener decorate(EventThread threadType, EventListener eventListener){
        switch (threadType){
            case UI:
                return new UIThreadEventListenerDecorator(eventListener, new Handler() );
            case NEW:
                return new AsynchronousEventListenerDecorator(eventListener);
            default:
                return eventListener;
        }
    }
}
