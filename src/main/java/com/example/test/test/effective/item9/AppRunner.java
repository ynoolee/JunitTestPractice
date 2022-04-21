package com.example.test.test.effective.item9;

public class AppRunner {
	public void wrongRun() {
		// 에러가 발생한 경우 정리를 하지 않고 끝내버리기 때문에 잘못된 예시
		MyResource myResource = new MyResource();
		myResource.doSomething(); // First Error
		myResource.close(); // Second Error
	}


	public void swallowFirstExceptionBySecondException() {
		// 가장 고전적인 에러 처리 방법 -> 코드가 장황해진다. close 코드 작성하기도 난감하다 , firstError(생성자에서), secondError(close에서)
		// 최초에 발생한 에러를 먼저 보고 싶을 거임.
		// 문제는 new 에서 FirstError 발생 -> close 에서 SecondError 발생 -> 두번째 에러가 첫 번째 에러를 잡아먹는다.

		MyResource myResource = null;
		try{
			myResource = new MyResource();
			myResource.doSomething(); // First Throw
		}finally {
			if(myResource != null){
				myResource.close(); // Second throw
			}
		}
	}

	public void refactorWithTryWithResource() {
		// 코드도 보기 좋아지고, 첫 번째 에러를 보여줌. 뒤의 에러는 쟁여놓음 (Suppressed: .... )
		// 이걸 사용하면 디버깅이 진짜 좋아짐.
		try(MyResource myResource = new MyResource()){
			myResource.doSomething();
		}
	}

	public void throwExceptionWithMultipleResource() {
		// myResource1 생성자에서 에러가 발생해도 close 는 둘 다 해줌 -> Exception 찍어 보면 된다.
		try(MyResource myResource1 = new MyResource();
			MySecondResource myResource2 = new MySecondResource()){
			myResource1.doSomething(); // exception 발생 -> 두 리소스 모두 close() 호출됨.
			myResource2.doSomething();
		}
	}

	public static void main(String[] args) {
		AppRunner appRunner = new AppRunner();
		appRunner.throwExceptionWithMultipleResource();

	}
}
