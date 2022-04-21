package com.example.test.test.learnjava.interfaceJava8.interfaceAndAbstract;

import java.time.LocalTime;
import java.util.Optional;
import org.apache.tomcat.jni.Local;

public interface AlarmClock {


	void setAlarm(LocalTime time);

	Optional<LocalTime> getAlarm();

	void fireAlarm();

	void stopBeeping();


	default long getSnoozeMinutes() {
		return 10L;
	}

	default void snooze() {
		stopBeeping();
		getAlarm().map(time -> time.plusMinutes(getSnoozeMinutes()))
			.ifPresent(this::setAlarm);
	}

	static LocalTime calculateAlarmTime(LocalTime bedTime, long hoursOfSleep) {
		var time = Optional.ofNullable(bedTime)
			.orElseGet(LocalTime::now);
		return time.plusHours(hoursOfSleep);
	}
}
