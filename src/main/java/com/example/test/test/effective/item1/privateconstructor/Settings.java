package com.example.test.test.effective.item1.privateconstructor;
/**
 *	이 클래스의 인스턴스는 #getInstance() 를 통해 사용 ( 자기가 갖고 있는 -> # 으로만 하면 된다.)
 * @see #getInstance()
 */

public class Settings {

	private boolean useAutoSteering;

	private boolean useABS;

	private Settings() {
	}

	private static final Settings SETTINGS = new Settings();
	// 기존에 존재하던 인스턴스를 제공해주는 것 -> getInstance 라는 네임 패턴을 사용하자
	public static Settings getInstance() {
		return SETTINGS;
	}

}
