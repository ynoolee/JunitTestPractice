package com.example.test.test.learnjava.interfaceJava8.interfaceAndAbstract;

import java.time.LocalTime;
import java.util.Optional;

// Java 8 이전의 인터페이스
// 이 인터페이스를 구현하는 모든 구현체에서는 아래의 메소드들 모두를 구현해야만 한다.
interface AlarmClockBefore8 {

	void setAlarm(LocalTime time);

	Optional<LocalTime> getAlarm();

	void fireAlarm();

	void stopBeeping();

	void snooze();

	long getSnoozeMinutes();
}
