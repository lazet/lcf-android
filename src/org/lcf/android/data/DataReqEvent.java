package org.lcf.android.data;

import java.util.Map;

import org.lcf.android.event.Event;


import static org.lcf.android.data.Constants.DATA_REQ_EVENT;

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
