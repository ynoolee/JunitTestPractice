package com.example.test.test.learnjava.prgrms.day4.optional;

import com.example.test.test.learnjava.prgrms.day4.collection.User;

public class Main {

	public static void main(String[] args) {

		User user = null; // new User(22,"gom"); <-- 이런 초기값은 실제 존재하는 유저일수도있어
		System.out.println(user);

		User u = User.EMPTY;
		User u2 = getUser();
		// 이제는 초기값으로 null 은 오지 않는다 하였었으니, 이제는 EMPTY 인지 아닌지를 확인
		if(u2 == User.EMPTY){

		}


	}

	private static User getUser() {
		return User.EMPTY;
	}
}
