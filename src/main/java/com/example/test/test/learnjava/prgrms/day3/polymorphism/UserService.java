package com.example.test.test.learnjava.prgrms.day3.polymorphism;

/**
 * 로그인 기능을 가진 구현체가 들어오면
 * 그 구현체를 통해서 로그인 기능을 수행 하는 것.
 * 로그인 이라는 인터페이스를 구현한 어떤구현체든 받아올 수 있다.
 * */
public class UserService implements Login{

	// Login 에 의존한다
	// 의존하는 의존체를 이 코드 내에서 생성하지 않고, 외부에 맡긴다면 -> 의존성을 낮추게 된다.
	// Login 과 UserService 사이의 관계가 생긴 것 -> 이런 것을 결합성이라고 한다.
	// 구상체와 관계를 맺고 있다면, 결합도가 높다고 하는데, 현채와 같이 추상체와 결합하면 결합도가 낮아진다 라고 한다
	// 의존서응ㄹ 외부로부터 전달받았다 ==> 의존성을 주입받았다.
	// 의존성 주입 (DI)
	// 의존을 할 때는, 구상체에 의존하지 말고, 추상체를 통해서 의존하여 "의존성의 역전" 이 일어나도록 하자.
	private Login login;

	// 로그인 관련한 것을 여기서 결정하지 말고 외부에서 받도록 하자
	public UserService(Login login){
		this.login = login;
	}

	@Override
	public void login() {
		login.login();
	}
}
