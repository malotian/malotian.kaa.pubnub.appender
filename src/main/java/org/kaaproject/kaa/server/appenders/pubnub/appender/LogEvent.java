package org.kaaproject.kaa.server.appenders.pubnub.appender;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

import org.kaaproject.kaa.common.dto.logs.LogEventDto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public final class LogEvent implements Serializable {

	private static final long serialVersionUID = -1L;

	private String channel;
	private HashMap<String, String> event;

	public LogEvent() {
	}

	public LogEvent(String channel, LogEventDto dto) {
		Gson gson = new Gson();
		Type fooType = new TypeToken<HashMap<String, String>>() {
		}.getType();
		HashMap<String, String> msg = gson.fromJson(dto.getEvent(), fooType);
		this.channel = channel;
		this.event = msg;
	
	}

	String getChannel() {
		return this.channel;
	}

	void setChannel(String channel) {
		this.channel = channel;
	}

	HashMap<String, String> getEvent() {
		return this.event;
	}

	void setEvent(HashMap<String, String> event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "LogEvent [channel=" + channel + ", event=" + event + "]";
	}

}