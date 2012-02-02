package org.lcf.android.event;

import org.lcf.android.event.EventManager;
import org.lcf.android.event.ObservesTypeListener;
import org.lcf.android.event.eventListener.factory.EventListenerThreadingDecorator;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class EventModule extends AbstractModule {

	@Override
	protected void configure() {
		final EventListenerThreadingDecorator observerThreadingDecorator = new EventListenerThreadingDecorator();
		 bindListener(Matchers.any(), new ObservesTypeListener(getProvider(EventManager.class), observerThreadingDecorator));

	}

}
