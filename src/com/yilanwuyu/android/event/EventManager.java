package com.yilanwuyu.android.event;

import roboguice.event.javaassist.RuntimeSupport;
import roboguice.util.Ln;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Manager class handling the following:
 *
 *   Registration of event observing methods:
 *      registerObserver()
 *      unregisterObserver()
 *      clear()
 *   Raising Events:
 *      fire()
 *      notifyWithResult()
 *
 * @author Adam Tybor
 * @author John Ericksen
 * @author lazet
 */
@SuppressWarnings({"unchecked"})
@Singleton
public class EventManager {
    //@Inject protected android.content.Context context;

    protected Map<String, Set<EventListener>> registrations = new HashMap<String, Set<EventListener>>();

    /**
     * Register the given EventListener to the contest and event class.
     *
     * @param eventName observed
     * @param listener to be triggered
     */
    public void registerObserver( String eventName, EventListener listener ) {
        Set<EventListener> observers = registrations.get(eventName);
        if (observers == null) {
            observers = new LinkedHashSet<EventListener>();
            registrations.put(eventName, observers);
        }

        observers.add(listener);

    }

    /**
     * Registers given method with provided context and event.
     *
     * @param instance to be called
     * @param method to be called
     * @param eventName observed
     */
    public void registerObserver(Object instance, Method method, String eventName) {
        registerObserver(eventName, new ObserverMethodListener(instance, method));
    }

    /**
     * Unregisters the provided event listener from the given event
     *
     * @param eventName observed
     * @param listener to be unregistered

     */
    public <T> void unregisterObserver(String eventName, EventListener listener ) {

        final Set<EventListener> observers = registrations.get(eventName);
        if (observers == null) return;

        for (Iterator<EventListener> iterator = observers.iterator(); iterator.hasNext();) {
            final EventListener registeredListener = iterator.next();
            if (registeredListener == listener) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Unregister all methods observing the given event from the provided context.
     *
     * @param instance to be unregistered
     * @param eventName observed
     */
    public <T> void unregisterObserver(Object instance, String eventName) {

        final Set<EventListener> observers = registrations.get(eventName);
        if (observers == null) return;

        for (Iterator<EventListener> iterator = observers.iterator(); iterator.hasNext();) {
            final EventListener listener = iterator.next();
            if( listener instanceof ObserverMethodListener ) {
                final ObserverMethodListener observer = ((ObserverMethodListener)listener);
                final Object registeredInstance = observer.instanceReference.get();
                if (registeredInstance == instance) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    /**
     * Raises the event's class' event on the given context.  This event object is passed (if configured) to the
     * registered observer's method.
     *
     * @param event observed
     */
    public void fire(Event event) {

        final Set<EventListener> observers = registrations.get(event.getName());
        if (observers == null) return;

        for (EventListener observer : observers)
            observer.onEvent(event);

    }

    public static class ObserverMethodListener implements EventListener {
        protected String descriptor;
        protected Method method;
        protected WeakReference<Object> instanceReference;

        public ObserverMethodListener(Object instance, Method method) {
            this.instanceReference = new WeakReference<Object>(instance);
            this.method = method;
            this.descriptor = method.getName() + ':' + RuntimeSupport.makeDescriptor(method);
            method.setAccessible(true);
        }

        public void onEvent(Event event) {
            try {
                final Object instance = instanceReference.get();
                if (instance != null) {
                    method.invoke(instance, event);
                } else {
                    Ln.w("trying to observe event %1$s on disposed context, consider explicitly calling EventManager.unregisterObserver", method.getName());
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final ObserverMethodListener that = (ObserverMethodListener) o;

            if (descriptor != null ? !descriptor.equals(that.descriptor) : that.descriptor != null) return false;
            final Object thisInstance = instanceReference.get();
            final Object thatInstance = that.instanceReference.get();
            return !(thisInstance != null ? !thisInstance.equals(thatInstance) : thatInstance != null);

        }

        @Override
        public int hashCode() {
            int result = descriptor != null ? descriptor.hashCode() : 0;
            final Object thisInstance = instanceReference.get();
            result = 31 * result + (thisInstance != null ? thisInstance.hashCode() : 0);
            return result;
        }

    }
}
