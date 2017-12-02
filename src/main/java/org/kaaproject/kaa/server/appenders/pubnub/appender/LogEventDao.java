package org.kaaproject.kaa.server.appenders.pubnub.appender;

import java.util.List;

import org.kaaproject.kaa.common.dto.logs.LogEventDto;

public interface LogEventDao {
	
    List<LogEvent> publish(String topic, List<LogEventDto> logEventDtos) throws Exception;
    
    void close();
    
}
