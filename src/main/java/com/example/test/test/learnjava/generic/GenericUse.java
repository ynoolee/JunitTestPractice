package com.example.test.test.learnjava.generic;

public class GenericUse {
    // 현재거 - 옆에거 빼는 거
    public static <T extends Comparable<T>> T getMax(T ... a) {

        T max = a[0];
        for (T temp : a) {
            if (temp.compareTo(max) > 0) max = temp;
        }
        return max;
    }
    public void wildCardMethod(MyCollection<?> collection){

    }
    public void genericMethod(MyCollection<String> collection){

    }
    public static <T extends Car> String getGenericCollection(MyCollection<T> collection, T tobeAddedData){
        collection.setData(tobeAddedData);
        Car data = collection.getData();
        System.out.println(data.getName());
        return data.getName();
    }
//     wildcard 는 type parameter와 달리 , wildcard 타입 으로 선언된 객체에는, 값일 지정,추가가 불가능하다.
//    public static void getWildcardCollection(MyCollection<?> collection){
//         collection.setData("abc");
//    }

    public void main(String[] args) {
        MyCollection<?> collection = new MyCollection<>();
//        collection.setData("A"); // 컴파일 에러 !!!! Required type :capture of ?  , Provided: String


    }

}
