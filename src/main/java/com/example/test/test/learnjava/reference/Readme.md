# Java 는 Call By value  
- Java 는 Call By reference 가 없다

자바에서는 Call by value 만이 있다. 
- call by value :값만 전달 하는 방식
  - 메소드에 값을 전달하고, 호출된 메소드에서 그 값에 대한 변화가 생기더라도 호출한 메소드 측에는 아무런 영향도 주지 않는다. 
- 여기서 혼란이 경우는 다음과 같은 경우일 것이다

```java
public class CallByValNRef {
    public static void main(String[] args) {
        CallByValNRef obj = new CallByValNRef();
        int a = 3;
        Integer A = 3;
        Int integerA = new Int(3);

        obj.doubleWithPrimitive(a);
        obj.doubleWithWrapper(A);
        obj.doubleWithReference(integerA);

        System.out.println(a); //3 
        System.out.println(A); // 3
        System.out.println(integerA.a); // 6
    }
    private void doubleWithReference(Int a){
        a.a *= 2;
    }

    public static class Int{
        int a;
        public Int(int a) {
            this.a = a;
        }
    }

```
호출한 쪽에서 전달해주었던 a 객체의 a 값이 바뀌게 된다.