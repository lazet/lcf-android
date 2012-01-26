package com.yilanwuyu.android.event.eventListener.factory;

import com.yilanwuyu.android.event.EventListener;
import com.yilanwuyu.android.event.EventThread;
import com.yilanwuyu.android.event.eventListener.AsynchronousEventListenerDecorator;
import com.yilanwuyu.android.event.eventListener.UIThreadEventListenerDecorator;

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
//            case BACKGROUND:
//                return new AsynchronousEventListenerDecorator<T>(eventListener);
            default:
                return eventListener;
        }
    }
}
