package com.yilanwuyu.android.demo;

import com.google.inject.Injector;
import com.google.inject.util.Modules;
import com.yilanwuyu.android.data.Constants;
import com.yilanwuyu.android.data.DataManager;
import com.yilanwuyu.android.event.EventModule;

import roboguice.RoboGuice;
import android.app.Application;

public class DemoApp extends Application {

	@Override
	public void onCreate() {
		Injector i = RoboGuice.setBaseApplicationInjector( this, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new EventModule()));
		i.injectMembers(this);
		DataManager dm = i.getInstance(DataManager.class);
		Constants.init("http://api.tongcard.net/", null);
		super.onCreate();
	}

}
