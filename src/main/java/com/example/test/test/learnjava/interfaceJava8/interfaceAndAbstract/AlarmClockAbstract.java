package com.example.test.test.learnjava.interfaceJava8.interfaceAndAbstract;

import java.time.LocalTime;
import java.util.Optional;

public abstract class AlarmClockAbstract {
	private LocalTime alarmTime;

	public static LocalTime calculateAlarmTime(LocalTime bedTime, long hoursOfSleep) {
		var time = Optional.ofNullable(bedTime)
			.orElse(LocalTime.now());
		return time.plusHours(hoursOfSleep);
	}

	// 울리고 있던 알람 끄고 다시 세팅
	public void setAlarm(LocalTime time){
		stopBeeping();
		this.alarmTime = time;
	}

	public Optional<LocalTime> getAlarm(){
		return Optional.ofNullable(this.alarmTime);
	}

	public abstract void fireAlarm();

	public void stopBeeping(){
		System.out.println("STOP BEEPING");
	}

	public long getSnoozeMinutes() {
		return 10L;
	}

	public void snooze() {
		stopBeeping();
		getAlarm().map(time -> time.plusMinutes(getSnoozeMinutes()))
			.ifPresent(this::setAlarm);
	}
}
