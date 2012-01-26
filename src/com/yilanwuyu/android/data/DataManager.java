package com.yilanwuyu.android.data;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.yilanwuyu.android.util.EncryptUtil;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.yilanwuyu.android.event.EventManager;
import com.yilanwuyu.android.event.EventThread;
import com.yilanwuyu.android.event.Observes;
import static com.yilanwuyu.android.data.Constants.DATA_REQ_EVENT;

@Singleton
public class DataManager{
    public static final int DEFAULT_POOL_SIZE = 5;
    
    protected Executor defaultExecutor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
    @Inject EventManager eventManager;

    @Observes(name="DATA_REQ_EVENT", threadType=EventThread.CURRENT)
	public void onEvent(DataReqEvent event) {
		defaultExecutor.execute(new DataTask(event,this.eventManager));
	}
	private static class DataTask implements java.lang.Runnable{
		private DataReqEvent event;
		private EventManager eventManager;
		public DataTask(DataReqEvent event,	EventManager eventManager){
			this.event = event;
			this.eventManager = eventManager;
		}
		public void run(){
			if(event == null){
				return;
			}
			HttpClient client=new DefaultHttpClient();
			//����Http����
			HttpPost post = new HttpPost(Constants.getServerAddr() + event.getAddr());
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			TreeMap<String,Object> tm = new TreeMap<String,Object>();
			if(event.getArgs() != null)
				tm.putAll(event.getArgs());
			if(event.getArgs() == null){
				for(Map.Entry<String, Object>  entry : tm.entrySet())
					qparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
			}
			String queryString = URLEncodedUtils.format(qparams, "UTF-8");
			
			//����У����
			String signature = EncryptUtil.genSign(queryString + Constants.getSignature());
			qparams.add(new BasicNameValuePair(Constants.SIGNATUE_NAME,signature ));
			try {
				post.setEntity(new UrlEncodedFormEntity(qparams, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��������
			String responseBody = null;
			try {
				BasicResponseHandler responseHandler = new BasicResponseHandler();
				responseBody = client.execute(post, responseHandler);
			}
			catch (IOException e) {
				Log.e(getClass().getName(), "Exception in DataManager", e);
			}
			//��json�����ؽ��,���ͽ���¼�
//			try {
				this.eventManager.fire(new DataRespEvent(this.event,responseBody));//new JSONObject(
//			} catch (JSONException e) {
//				Log.e(getClass().getName(), "Exception in Processing Json in DataManager::" + responseBody, e);
//			}
		}
		
	}
}
