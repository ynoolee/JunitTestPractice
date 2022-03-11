package com.example.test.test.learnjava.reflectionStudy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class DemoTest {
  public static void main(String[] args) {
    DemoClass dc = new DemoClass();

    DemoTest dt = new DemoTest();
    dt.getClassInfos(dc);

  }
  public void getClassInfos(Object clazz){
    Class demoClass = clazz.getClass();
    getClassInfo(demoClass);
    getFieldInfo(demoClass);
    getMethodInfo(demoClass);
  }
  public void getClassInfo(Class demoClass){
    String className = demoClass.getName();
    System.out.printf("Class Name : %s\n",className);
    String canonicalName = demoClass.getCanonicalName();
    System.out.printf("Class canonicla Name: %s\n",canonicalName);
    String classSimpleName = demoClass.getSimpleName();
    System.out.printf("Class Simple Name: %s\n",classSimpleName);
    String packageName = demoClass.getPackage().getName();
    System.out.printf("Package Name: %s\n",packageName);
    String toSt = demoClass.toString();
    System.out.printf("toString: %s\n",toSt);
  }
  public void getFieldInfo(Class demoClass){
    System.out.println("========================================");
    Field[] field1 = demoClass.getDeclaredFields();
    Field[] field2 = demoClass.getFields();
    System.out.printf("Declared Fields: %d, Fields: %d\n", field1.length, field2.length);

    for (Field field : field1) {
      String fieldName = field.getName();
      int modifiers = field.getModifiers();
      String modifierStr = Modifier.toString(modifiers);
      String type = field.getType().getSimpleName();
      System.out.printf("%s %s %s\n",modifierStr, type, fieldName);
    }
  }
  public void getMethodInfo(Class demoClass){
    System.out.println("=======================================");
    Method[] method1 = demoClass.getDeclaredMethods();
    Method[] method2 = demoClass.getMethods();
    System.out.printf("Declared methods: %d, Methods: %d\n", method1.length, method2.length);
    for (Method method : method1) {
      // method name info
      String methodName = method.getName();
      // method modifier info
      /**
       * int 타입으로 리턴해온다.
       * Modifier 클래스의 static toString() 메서드에서는  전달되어온 int 타입의 값에 대한 식별자 정보를 문자열로 리턴한다.
       * */
      int modifiers = method.getModifiers();
      String modifierStr = Modifier.toString(modifiers);
      // method return type info
      String returnType = method.getReturnType().getSimpleName();
      // method parameter info
      Class<?>[] params = method.getParameterTypes();
      StringBuilder sb = new StringBuilder();
      int paramLen = params.length;
      if(paramLen != 0){
        sb.append(params[0].getSimpleName()).append(" arg");
        for( int loop = 1; loop < paramLen; loop++){
          sb.append(", ").append(params[loop].getName()).append(" arg").append(loop);
        }
      }
      // method exception info
      Class<?>[] exceptions = method.getExceptionTypes();
      StringBuilder sb2 = new StringBuilder();
      int exceptionLen = exceptions.length;
      if ( exceptionLen != 0){
        sb2.append(" throws")
                .append(exceptions[0].getSimpleName());
        for( int loop = 1; loop< exceptionLen;loop++){
          sb2.append(",").append(exceptions[loop].getSimpleName());
        }

      }
      System.out.printf("%s %s %s(%s) %s\n", modifierStr, returnType, methodName, sb, sb2);
    }

  }
}
