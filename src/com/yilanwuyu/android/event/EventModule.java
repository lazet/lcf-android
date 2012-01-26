package com.yilanwuyu.android.event;

import com.yilanwuyu.android.event.eventListener.factory.EventListenerThreadingDecorator;
import com.yilanwuyu.android.event.EventManager;
import com.yilanwuyu.android.event.ObservesTypeListener;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class EventModule extends AbstractModule {

	@Override
	protected void configure() {
		final EventListenerThreadingDecorator observerThreadingDecorator = new EventListenerThreadingDecorator();
		 bindListener(Matchers.any(), new ObservesTypeListener(getProvider(EventManager.class), observerThreadingDecorator));

	}

}
