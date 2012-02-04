package org.lcf.android.data;

import static org.lcf.android.data.Constants.DATA_ERROR;
import static org.lcf.android.data.Constants.DATA_ERROR_EVENT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lcf.android.event.Event;

public class DataErrorEvent extends Event{
	
	public DataErrorEvent(DataReqEvent req,Exception e){
		super(DATA_ERROR_EVENT + "/" + req.getAddr(),req.getAddr(),buildResultMap(req,e));	
	}
	protected static Map<String,Object> buildResultMap(DataReqEvent req,Exception e){
		Map<String,Object> argv = null;
		
		if(req.getArgs() != null){
			argv = req.getArgs();
		}
		else{
			argv = new HashMap<String,Object>();
		}
		argv.put(DATA_ERROR, e);
		return argv;
	}
}