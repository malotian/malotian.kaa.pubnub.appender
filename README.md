# malotian.kaa.pubnub.appender

clone `https://github.com/kaaproject/kaa` repository (to let say `kaa-master`)

copy `malotian.kaa.pubnub.appender` to `kaa-master/server/appenders/`

update `kaa-master/server/appenders/pom.xml`, to include `malotian.kaa.pubnub.appender` as new module (`<module>malotian.kaa.pubnub.appender</module>`)

	<modules>
	<module>file-appender</module>
	<module>mongo-appender</module>
	<module>flume-appender</module>
	<module>rest-appender</module>
	<module>malotian.kaa.pubnub.appender</module>
	</modules>
