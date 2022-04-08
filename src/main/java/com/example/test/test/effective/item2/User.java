package com.example.test.test.effective.item2;

import java.time.LocalTime;

public class User {
	private final String email;
	private final String name;
	private final String password;
	private final String introduce;
	private final LocalTime createAt;
	private final LocalTime lastLoginAt;

	public User(String email, String name, String password, LocalTime createAt,
		LocalTime lastLoginAt, String introduce) {
		// Product 클래스인 User 의 생성자 로직 내부에 Validation 하기
		// null 이 오면 안되는 값들에 대한 검증

		if (email == null) {
			throw new IllegalArgumentException("eamil must be [provided");
		}
		if (password == null) {
			throw new IllegalArgumentException("eamil must be [provided");
		}

		this.email = email;
		this.name = name;
		this.password = password;
		this.introduce = introduce;
		this.createAt = createAt;
		this.lastLoginAt = lastLoginAt;
	}

	// 이메일, 비밀번호, 자기소개 정보를 갖고 생성하는 생성자
	public User(String email, String password, String introduce) {
		this.email = email;
		this.introduce = introduce;
		this.password = password;
		this.name = null;
		this.createAt = null;
		this.lastLoginAt = null;

	}

	public static class Builder{
		// Product class 의 모든 필드들을 갖는다 -> 코드의 중복이 존재함..
		private final String email;
		private final String password;
		// optional field 들
		private String name;
		private String introduce;
		private LocalTime createAt;
		private LocalTime lastLoginAt;

		public Builder(String email, String password) {
			this.email = email;
			this.password = password;
		}

		// Named setter 를 사용
		// Builder 를 리턴함으로서 method chaining 이 가능하다. -> 클라이언트측에서 가독성 좋은 코드 작성 가능.
		public Builder name(String name){
			this.name = name;
			return this;
		}
	}


	// 이메일, 비밀번호, 이름 정보를 갖고 생성하는 생성자 정의하고 싶지만
	// 같은 시그니쳐를 가진 생성자를 정의할 수 없다는 한계
//	public User(String email, String password, String name) {
//		this.email = email;
//		this.name = name;
//		this.password = password;
//	}

	// Java 언어의 한계 : Named parameter 를 가질 수 없다.
//	public User(String email = null, String password = null, String name = null, String introduce = null, LocalDateTime createAt = null, LocalDateTime lastLoginAt = null){
//		this.email = email;
//		this.introduce = introduce;
//		this.password = password;
//		this.name = name;
//		this.lastLoginAt = lastLoginAt;
//		this.createAt = createAt;
//	}

	public String getIntroduce() {
		return introduce;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public LocalTime getCreateAt() {
		return createAt;
	}

	public LocalTime getLastLoginAt() {
		return lastLoginAt;
	}
}
