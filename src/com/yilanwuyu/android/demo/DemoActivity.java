package com.yilanwuyu.android.demo;

import roboguice.activity.RoboActivity;

import com.yilanwuyu.android.data.DataReqEvent;
import com.yilanwuyu.android.event.Event;
import com.yilanwuyu.android.event.EventManager;
import com.yilanwuyu.android.event.EventThread;
import com.yilanwuyu.android.event.Observes;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.inject.Inject;

public class DemoActivity extends RoboActivity {
    
    @Inject public EventManager em;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.eventdemo);
    }

    public void onClickMe(View v)
    {
    	Toast.makeText(DemoActivity.this, "send request to "+"api", Toast.LENGTH_LONG).show();
    	 em.fire(new DataReqEvent("api",null));
    }
   
    @Observes(name="DATA_RESP_EVENT",threadType=EventThread.UI)
    protected void handleEvent(Event event){
    	Toast.makeText(DemoActivity.this, ((String)event.getArgs().get("DATA_RESP_EVENT_RESULT")).substring(0, 100), Toast.LENGTH_LONG).show();
    }
    
}
