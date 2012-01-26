package com.yilanwuyu.android.data;

import java.util.HashMap;
import java.util.Map;

import com.yilanwuyu.android.event.Event;

import static com.yilanwuyu.android.data.Constants.DATA_RESP_EVENT;
import static com.yilanwuyu.android.data.Constants.DATA_RESP_EVENT_RESULT;

public class DataRespEvent extends Event{
	
	public DataRespEvent(DataReqEvent req,Object result){
		super(DATA_RESP_EVENT,req.getAddr(),buildResultMap(req,result));	
	}
	protected static Map<String,Object> buildResultMap(DataReqEvent req,Object result){
		Map<String,Object> argv = null;
		
		if(req.getArgs() != null){
			argv = req.getArgs();
		}
		else{
			argv = new HashMap<String,Object>();
		}
		argv.put(DATA_RESP_EVENT_RESULT, result);
		return argv;
	}
}
