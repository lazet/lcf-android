package com.yilanwuyu.android.data;

import java.util.Map;

import com.yilanwuyu.android.event.Event;

import static com.yilanwuyu.android.data.Constants.DATA_REQ_EVENT;

public class DataReqEvent extends Event{
	
	/**
	 * 数据请求事件定义
	 * @param url
	 * @param args
	 */
	public DataReqEvent(String url, Map<String,Object> args) {
		super(DATA_REQ_EVENT,url,args);
	}
	
}
