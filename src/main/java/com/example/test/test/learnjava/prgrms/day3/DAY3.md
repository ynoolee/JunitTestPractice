# 인터페이스 이야기

## 1. 인터페이스의 기능


### (1) 구현을 강제하는 기능 
- 모든 메소드가 ***추상 메소드***로 이루어진 클래스-> 인터페이스 --> 구현부가 없기에, 이를 implement 하는 애들은 "구현이 강제"된다
### (2) 다형성
- 다형성을 제공한다는 것은 추상체로서의 역할을 한다는 것 --> 추상체로서의 역할을 해주면서 구상체와 호출하는 측의 디펜던시를 끊어지고 의존성을 역전시키는 역할을 하게 된다.  
```java
interface MyRunnable{
	void myRun();
}
interface YourRunnable{
	void yourRun();
}

public class Main implements MyRunnable, YourRunnable{

	public static void main(String[] args) {
		Main m = new Main();
		m.myRun();
		m.yourRun();

		// 호출할 수 있는 메소드를 제한 할 수 있다.
		MyRunnable mr = new Main();
		mr.myRun();
		mr.yourRun(); // Compile Error

		YourRunnable yr = new Main();
		yr.myRun(); // Compile Error
		yr.yourRun();;
	}
	@Override
	public void myRun() {
	}
	@Override
	public void yourRun() {
	}
}
```
추가적 예시 : polymorphism 패키지를 확인할 것. 

### (3) 결합도를 낮추는 효과 ( 의존성 역전)


## 2. default method 기능 
인터페이스를 사용하다보니 아쉬운 부분이 생겼고 따라서 Java 8 부터 인터페이스의 기능 개선이 있었다
- 인터페이스가 구현체를 가질 수 있게 된 것. ==> 즉, 
- default method


이런상황이 존재한다
```java
// 이 서비스에서는 method1 만 사용할 거임
// 그럼에도 method2 를 구현하지 않으면 오류가 떠서 -> 쓸모없는 구현 또는 아무것도 적어주지않음
class Service implements MyInterface{

	@Override
	public void method1() {
		System.out.println("Hello World");
	}

	@Override
	public void method2() {
		// nothing
	}
}
```
이럴 때는 Adapter 라는 것을 만들어주면 좋다
> 인터페이스에는 수많은 메소드가 존재하는데, 나는 이 중 몇 개만 구현해서 사용하고 싶은 경우. 이런식으로 어댑터를 사용할 수 있었다. 
> - 어댑터에는 인터페이스의 모든 추상 메소드에 대한 기본 구현을 해 둠으로서, MyInterface 의 일부 기능만을 오버라이드 하고 싶은 곳에서는, MyInterfaceAdapter 를 상속받아 오버라이드 하면 되었을 것
> - 하지만, 자바에서는 다중상속이 되지 않는다는 한계점이 있었을 것 같다. 
```java
public class MyInterfaceAdapter implements MyInterface{
	@Override
	public void method1() {
	}
	@Override
	public void method2() {
	}
}
```
이런식으로 어댑터를 두고는, Service 에서는 MyInterface 를 구현하는게 아니라 어댑터를 확장하는 것으로 변경
```java
class Service extends MyInterfaceAdapter{
	@Override
	public void method1() {
		System.out.println("Hello World");
	}
}
```

> 문제상황 : 자바에서는 상속을 하나밖에 못 받는다는것. 
> - 이미 다른 것을 상속받고 있는 경우라면, 어쩔수없이 MyInterface 를 구현해 줘야하게 된다. (그리고는 사용하지도 않는 메소드를 empty 하게 오버라이드해놓겠지) => 지저분하게도, 구현하지 않은 비어있는 메소드를 갖게 된다.

- 이를 해결하고싶어서 나온게 디폴트 메소드인 것 

```java
class Service extends Object implements MyInterface{
	@Override
	public void method1() {
		System.out.println("Hello World");
	}
}
```


> 기존의 전통적인 인터페이스로는, 같은 역할을 하는 구현이더라도, 각 구현체에서 중복하여 구현을 해 줘야 했다. 
```java
interface Flyable{
	void fly();
}

interface Swimmable {
	void swim();
}

interface Walkable {
	void walk();
}

class Duck implements Swimmable, Walkable {

	@Override
	public void swim() {
		System.out.println("Duck.swim");
	}

	@Override
	public void walk() {
		System.out.println("Duck.walk");
	}
}

class Swan implements Flyable, Walkable {

	@Override
	public void fly() {
		System.out.println("Swan.fly");
	}

	@Override
	public void walk() {
		System.out.println("Swan.walk");
	}
}
```

구현의 변경 없이, 인터페이스를 하나 추가해 주는 것만으로도 "기능이 확장" 될 수 있게 된다. 
- swan 이 수영을 할 수 있게  하고 싶으면 이제는 Swan 이 Swimmable 을 구현하는 것으로 하나 더 추가해주면되는 것.
```java
class Swan implements Flyable, Walkable,Swimmable {
}

```
> 인터페이스 추가만으로도 "기능을 확장" 할 수 있게 되었다. 
- 기존의 "구현을 강제화" 하거나, 의존성을 낮추기 위해 추상클래스로 존재하는것만이 아닌, 
- 적극적으로 "어댑터역할" 을 수행하기도, "기능을 확장" 시키는 역할을 하기도 하게 되었음 


- static method 도 가질 수 있게 되었음 
  - 인터페이스 자체의 메소드도 호출할 수 있게 되었음
  - 그러면 utility 를 만들 수도 있음.
  - 함수 제공자가 되었다
    - 메소드 : 클래스에 종속된 것 -> 인스턴스를 생성해야지만 사용가능
    - Java 8 부터 Function 이라고 부르는 것들이 추가됨


## 3. Functional Interface
- 추상 메소드가 하나만 존재하는 인터페이스
  - default 나 static 메소드가 있어도 상관없다
- @FunctionalInterface 를 달아준다.
- 함수형 인터페이스에 있는 추상메소드를 함수라고 부른다.


## 3.1. 인터페이스 임시 생성하기 
- 익명클래스를 사용해서 인터페이스를 생성하고 구현을 바로 정의한다 


인터페이스를 사용하려면 그 인터페이스 구현체를 인스턴스화 해야지만 사용할 수 있다.
그러다보니 자꾸만 임시적인 클래스들이 생겨났음
 
```java
```


익명 클래스를 만들어서 처리할 수 있다면, 메소드도 익명으로 만들 수는 없을까?


## 4. Lambda 표현식
- 익명 메소드를 사용해서 간결한 인터페이스 인스턴스를 생성하는 방법.
- 함수형 인터페이스에서 가능하다. ( 추상메소드가 여러개라면, 어떤 메소드에 대한 정의인지 컴파일러는 알 수 없으니)
- 간결하게 표현이 가능하다. 

## 4.1 메소드 레퍼런스
- 람다 표현식에서 입력되는 값을 변경없이 바로 사용하는 경우
  - "변경없이바로 사용하는경우" 에만!
  - 변경이 일어나는 경우는 이를 사용할 수 없음
- 최종으로 적용되 메소드의 레퍼런스를 지정해 주 표현방식.
- 쓰면 뭐가 좋을까?? 들어오는 값을 변경없이 사용된다는 것이 "보장" 하게끔 하는 "의지의 표현"
- 입력값을 변경하지 말라는 표현방식이기도 하다!!!!!!!!
  - 개발자가 값을 변경할 여지 자체가 차단되는 것 
  - 개발자의 개입을 차단함으로서 안정성을 얻을 수 있다고도 할 수 있음.


### 제네릭

만약 MySupplier 는 String 이 아닌 것을 리턴하고 싶어지고
Mapper 는 또 서로 다른 것을 매핑하고 싶어진다면?? 그 때 마다 Mapper, Supplier 를 새로 만들건가?
제네릭쓰면좋겠지 

### 자바의 함수형 인터페이스
MyMapper, MySupplier , MyConsumer 와 같은 것을 자바에서 만들어놓았다.
java.util.function 패키지에 이와 같은 것들이 정의되어있다. 

우리의 Mapper 같이 INPUT을 받고 ouput 을 내는 Function 이라는 애가 있음
- 그럼 Input 이 두개면 어떡하냐 ? --> 그래서 Bifunction 이라는 것도 있음
- 똑같이 Biconsumer 라는 것도 있음. 
- 
