package org.lcf.android.demo;

import org.lcf.android.data.Constants;
import org.lcf.android.data.DataManager;
import org.lcf.android.event.EventModule;

import com.google.inject.Injector;
import com.google.inject.util.Modules;

import roboguice.RoboGuice;
import android.app.Application;

public class DemoApp extends Application {

	@Override
	public void onCreate() {
		//以下三行保持不变
		Injector i = RoboGuice.setBaseApplicationInjector( this, RoboGuice.DEFAULT_STAGE, Modules.override(RoboGuice.newDefaultRoboModule(this)).with(new EventModule()));
		i.injectMembers(this);
		DataManager dm = i.getInstance(DataManager.class);
		//修改服务器地址
		Constants.init("http://api.tongcard.net/", null);
		super.onCreate();
	}

}
