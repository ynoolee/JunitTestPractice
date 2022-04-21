package com.example.test.test.learnjava.inner;

public class Car {
	static private String whichStuff = "car"; // 이 물건은 무엇인가
	private String carName;
	private String carType;


	public Car() {
		System.out.println("Car.Car");
	}


	public Car(String carName, String carType) {
		this.carName = carName;
		this.carType = carType;
	}

	private String getCarName() {
		return this.carName;
	}

	// inner class
	class Engine {

		private String engineType;

		public Engine() {
			System.out.println("Engine.Engine");
		}

		void setEngine() {

			// 아우터 클래스의 프로퍼티에 접근 : using "this"
			if (Car.this.carType.equals("4WD")) {
				// 아우터 클래스의 " private 메소드 " 에 접근
				if (Car.this.getCarName().equals("Crysler")) {
					this.engineType = "Smaller";
				} else {
					this.engineType = "Bigger";
				}
			} else {
				this.engineType = "Bigger";
			}
		}

		//
		void forWhichCar() {
			// 아우터 클래스의 프로퍼티에 접근 : this 를 사용하지 않음
			System.out.println(carType);
			// 아우터 클래스의 프라이빗 메소드에 접근
			System.out.println(getCarName());
		}


		String getEngineType() {
			return this.engineType;
		}
	}

	static class Motor {

		static private String motorType;
		private String electricType;
		private Car car;

		public Motor(Car car) {
			this.car = car;
		}


		void forWhichCar() {
			System.out.println(Car.whichStuff); // static method 도 아니지만 outer class 의 static private member 는 접근 가능하다
//			System.out.println(Car.this.getCarName()); //
			System.out.println(car.getCarName());
		}

		// static method 에서는 static member 에만 접근 가능
		protected static String getMotorType(){
			return motorType;
		}

		protected String getElectricType(){
			return electricType;
		}

	}
}
