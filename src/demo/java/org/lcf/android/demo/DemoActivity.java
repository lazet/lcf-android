package org.lcf.android.demo;

import java.util.HashMap;
import java.util.Map;

import org.lcf.android.data.DataReqEvent;
import org.lcf.android.event.Event;
import org.lcf.android.event.EventManager;
import org.lcf.android.event.EventThread;
import org.lcf.android.event.Observes;

import roboguice.activity.RoboActivity;

import org.lcf.android.demo.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.inject.Inject;

/**
 * Activity 继承RoboActivity
 * @author Administrator
 *
 */
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
    	Map<String,Object> args = new HashMap<String,Object>();
    	args.put("method", "doit");
    	 em.fire(new DataReqEvent("api",args));
    }
   
    @Observes(name="DATA_RESP_EVENT/api",threadType=EventThread.UI)
    protected void handleEvent(Event event){
    	Toast.makeText(DemoActivity.this, ((String)event.getArgs().get("DATA_RESP_EVENT_RESULT")).substring(0, 100), Toast.LENGTH_LONG).show();
    }
    @Observes(name="DATA_ERROR_EVENT/api2",threadType=EventThread.UI)
    protected void handleErrorEvent(Event event){
    	Toast.makeText(DemoActivity.this, "request failed!", Toast.LENGTH_LONG).show();
    }
}
