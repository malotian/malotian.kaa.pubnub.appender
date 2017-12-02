package org.kaaproject.kaa.server.appenders.pubnub.appender;

import java.util.ArrayList;
import java.util.List;

import org.kaaproject.kaa.common.dto.logs.LogEventDto;
import org.kaaproject.kaa.server.appenders.pubnub.config.gen.PubNubConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;

public class LogEventPubnubDao implements LogEventDao {

	private static final Logger LOG = LoggerFactory.getLogger(LogEventPubnubDao.class);

	private PubNub pubNub;

	public LogEventPubnubDao(PubNubConfig configuration) throws Exception {

		PNConfiguration pnConfiguration = new PNConfiguration();
		pnConfiguration.setSubscribeKey(configuration.getSubscribeKey().toString());
		pnConfiguration.setPublishKey(configuration.getPublishKey().toString());

		this.pubNub = new PubNub(pnConfiguration);
	}

	@Override
	public List<LogEvent> publish(String channel, List<LogEventDto> logEventDtos) throws Exception {
		List<LogEvent> logEvents = new ArrayList<>(logEventDtos.size());
		for (LogEventDto logEventDto : logEventDtos) {
			LogEvent logEvent = new LogEvent(channel, logEventDto);

			logEvents.add(logEvent);

			LOG.debug("Publishing {} log event with configuration {}", logEvent, this.pubNub);
			try {
				this.pubNub.publish().message(logEvent.getEvent()).channel(logEvent.getChannel()).usePOST(true).sync();
			} catch (PubNubException pnex) {
				LOG.debug("Got PubNubException... {} exception", pnex);
				throw pnex;
			}
			LOG.debug("Log {} published to channel.", logEvent.getEvent());
		}
		return logEvents;
	}

	@Override
	public void close() {
		// No operation
	}
}
