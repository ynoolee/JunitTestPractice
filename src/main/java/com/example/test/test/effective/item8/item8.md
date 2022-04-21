- 이식성 -> Java 9  에서 finalizer 가 deprecated 되어서 이렇게 말하는 것 같다 
- Garbage collection 될 때 이 메소드(finalize()) 를 호출 해 준다. 
  - 이 메소드가 언제 호출 될 지는 예측하지 못함. 
  - 이 객체가 gc의 대상이 된다고 해서 바로 gc가 되지 않는 것 처럼.
  - finalize() -> 예측이 불가능함.
```java
public class SampleRunner {
	public static void main(String[] args) throws InterruptedException {
		SampleRunner sampleRunner = new SampleRunner();
		sampleRunner.run();
		Thread.sleep(1000L);
		// 실행시켜보면 -> 다 끝나도 -> 해당 인스턴스에 대한 참조가 더이상 유효하지 않다고 finalize() 호출 되지는 않았음
		// gc 의 대상이 되기는 하지만, gc 가 바로바로 호출되지는 않기 때문
	}

	// FinalizerExample 인스턴스의 스코프(유효성 범위)는 이 메소드로 한정되지
	// 이 메소드가 끝남과 동시에 이 인스턴스의 유효성이 끝나 -> gc의 대상이됨
	private void run(){
		FinalizerExample finalizerExample = new FinalizerExample();
		finalizerExample.hello();
	}
}

```
- 성능 문제  why?
  - gc 시에 반드시 finalize() 실행하다보니.

- Java 에서 자원 반남은 ```try-with-resources``` 와 ```try-finally```로 하는 것이지 finalize() 로 하는게 아니다.

1. 언제 실행 될지 모른다.

2. 아예 실행이 되지 않을 수도 있다. (인스턴스 유효성이 끝낫음에도 아예 실행이 되지 않은 예시를 보았음)
- 심지어 System.gc() 호출한다고 바로 Garbage Collection 되는 것도 아니라는 것에 일단 항상 주의해야함.

3. 인스턴스 반납을 지연시킬 수 있다. 
- finalize() 실행시키는 별도의 스레드는 우선순위가 낮은 편임.. => 밀리다 보면 해당 인스턴스의 반납이 미뤄지다보니 OOME 발생 가능성

4. 보안 이슈 
- A 클래스 와, 이를 상속한 B 클래스가 존재한다고 해 보자. 
B 클래스가 인스턴스 생성시 예외를 던진다. 그리고 finalize() 를 overriding 해 놓았다고 하자.
- 인스턴스 생성할 때 예외가 던져졌을 때, 아예 이 객체가 죽어야 정상인데, 죽을 때 finalize() 가 실행되고 -> finalize() 메소드 내에서 이 인스턴스가 가진 메소드, static field 접근이 가능.
아니면 이 인스턴스가 gc 못하게, 죽지 못하게 할 수도 있고 
- 노출되지 않아야 할 인스턴스 메소드까지 사용할 수 있게 되어버릴 수도 있다. 
- A 클래스의 finaalize() 메소드 자체를 final로 선언해두면 좋겠다. 

try-with-resources

- 자원 반납해야하는 리소스 있다면, Autoclosable 라는 인터페이스를 구현 해 줄 수가 있다. 
- 리소스를 사용한 측에서는 반드시 close() 를 호출 해 줘야한다. 

- 이를 보장하기 위해 try-finally 를 쓰지

```java
public static void main(String[] args) throws Exception {
  SampleResource sampleResource = null;
  try{
  sampleResource = new SampleResource();
  sampleResource.hello();
  }finally {
  if( sampleResource != null){
  sampleResource.close();
  }
  }

  }
```


```java
	public static void main(String[] args) throws Exception {
		try (SampleResource sampleResource = new SampleResource()) {
			sampleResource.hello();
		}
	}

```
> 방금 이게 이게 가장 이상적인 자원반납하는 방법임

finalize, cleaner 는 언제? 자원반납해야하는 리소스는 원래 추천하기로는 클라이언트측에서 명시적으로 close() 를 해야하.ㅁ
클로즈를 클라엉ㄴ트가 안했을 수도 있다. 
이 때에대한 안전망으로 삼아 finalize() 를 안정망을 삼는거지 



- 두 가지 방법으로 자원 정리 할 수 있음
  - try - with - resource  ==> Autoclosable 만 있으면  ==> Autoclosable 은 Cleaner의 clean 을 호출. 결국 우리는 클리너를 사용해서 클린을 하게 된다. 클리너 자체도 등해 놨었는데, 객체를 클린하면 이 클리너도 필요없게 되고 -> 어느 순간 gc의 대상이 되면 이 클리너가 동작을 하겠지.  
  - 



