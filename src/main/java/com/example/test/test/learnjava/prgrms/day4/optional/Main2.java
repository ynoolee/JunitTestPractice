package com.example.test.test.learnjava.prgrms.day4.optional;

import com.example.test.test.learnjava.prgrms.day4.collection.User;
import java.util.Optional;

public class Main2 {

	public static void main(String[] args) {
		User user = null;
		// emtpy 라는 값이 있다는ㄱ ㅓㅅ을 아는 사람끼리만 통용할 수 있다보니 좋지 않음.
		// null 일 수도 null 이 아닐 수도 있다는 것을 표현하는 타입 탄생
		Optional<User> optionalUser = Optional.ofNullable(user); // null 이 될 수 있는 값들을 운반하는 캐리어

		optionalUser.isEmpty();// 	값이 없으면 트루
		optionalUser.ifPresent(u-> System.out.println(u)); // 값이 있으면 트루

		if (optionalUser.isPresent()){
			// do 1
		}else{
			// do 2
		}

		if(optionalUser.isEmpty()){
			// do 1
		}else{
			// do 2
		}
		// 위의 if else 구문과 같은 표현이다
		optionalUser.ifPresentOrElse(u ->{
			// do 1
		}, () -> {
			// do 2
		});

		// Optional 받은 사람은 적절한 기능을 사용하도록 하는 것을 강제화할 수 있다.
		// Optional 을 리턴하는 것은, 이 자체에 " null 일 수도 있고 아닐 수도 있으니, 받은 사람이 잘 처리하세요" 라는 정보가 포함된 리턴 정보인 것

	}
}
