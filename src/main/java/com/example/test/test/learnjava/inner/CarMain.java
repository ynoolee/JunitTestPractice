package com.example.test.test.learnjava.inner;


public class CarMain {

	public static void main(String[] args) {
		Car car1 = new Car("Mazda", "8WD");

		// 아우터 클래스를 사용하여 이너 클래스 객체 생성
		Car.Engine engine = car1.new Engine();
		engine.setEngine();


	}

	public void testNestedStaticClass(){
		Car car1 = new Car("Mazda", "8WD");

		// static nested class 에 대한 접근
		System.out.println(Car.Motor.getMotorType()); // static nested class 에 static 으로 선언된 멤버 접근가능 ( static nested class 자체가, Car 라는 클래스의 정적 멤버와도 같으니까)
//		System.out.println(Motor.getElectricType()); // static nested class 에 non-static 으로 선언된 멤버 접근 불가능
		Car.Motor motor = new Car.Motor(car1);
		System.out.println(motor.getElectricType());
	}

}
