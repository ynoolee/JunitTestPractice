package com.example.test.test.learnjava.generic;

public class GenericUse {

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


    public void main(String[] args) {
        MyCollection<?> collection = new MyCollection<>();
//        collection.setData("A"); // 컴파일 에러 !!!! Required type :capture of ?  , Provided: String


    }

}
