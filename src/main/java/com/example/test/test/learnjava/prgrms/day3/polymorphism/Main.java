package com.example.test.test.learnjava.prgrms.day3.polymorphism;

public class Main {

	public static void main(String[] args) {
		// want to Login
		// 각각의 인스턴스를 생성 ( 어떤 로그인 객체 만들지 내가 결정했음)
		KakaoLogin kakaoUser = new KakaoLogin();
		kakaoUser.login();

		NaverLogin naverUser = new NaverLogin();
		naverUser.login();

		// 어떤 방식으로 로그인할 지 여부를 내가 결정하지 않을 경우
		Login user1 = getLoginV1();
		user1.login();

		// 어떤 방식으로 로그인 할 지 여부를 내가 결정하지 않는 경우
		Login user2 = getLogin(LoginType.Naver);
		user2.login();

		// 실행하는 호스트 코드 측에서 " 어떤 것으로 로그인할지 결정"
		// 설정파일, config 를 읽었더니 네이버로 로그인해야한다면 네이버로 로그인하도록 할 수도 있다.
		new Main().run(LoginType.Kakao);

		// UserService 가 존재하는 경우
		UserService userService = new UserService(new KakaoLogin());
		userService.login();
	}

	// 이것만 변경하면 로그인 방식 변경 가능
	private static Login getLoginV1() {
		return new KakaoLogin();
	}
	// 팩토리 패턴
	private static Login getLogin(LoginType type){
		if(type == LoginType.Kakao) return new KakaoLogin();
		return new NaverLogin();
	}

	// 로그인 타입을 받아와 로그인하도록 한다
	void run(LoginType loginType){
		// 다형성을 이용해 인터페이스타입으로 표현한다면, 구현체를 모르고도 사용가능
		Login user = getLogin(loginType);
		user.login();
	}
}
