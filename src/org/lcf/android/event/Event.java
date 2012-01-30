package org.lcf.android.event;

import java.util.Map;

public class Event {
	public Event(String name, String addr, Map<String,Object> args) {
		this.name = name;
		this.addr = addr;
		this.args = args;
	}
	private String name;
	private String addr;
	private Map<String,Object> args;
	
	public String getAddr() {
		return addr;
	}
	public String getName(){
		return name;
	}
	public Map<String,Object> getArgs() {
		return args;
	}
}
