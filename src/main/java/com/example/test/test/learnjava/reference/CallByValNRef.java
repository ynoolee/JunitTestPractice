package com.example.test.test.learnjava.reference;

public class CallByValNRef {
    public static void main(String[] args) {
        CallByValNRef obj = new CallByValNRef();

        int a = 3;
        Integer A = 3;
        Int integerA = new Int(3);

        obj.doubleWithPrimitive(a);
        obj.doubleWithWrapper(A);
        obj.doubleWithReference(integerA);

        System.out.println(a);
        System.out.println(A);
        System.out.println(integerA.a);
    }

    private void doubleWithPrimitive(int a){
        a *= 2;
    }

    private void doubleWithWrapper(Integer a){
        a *= 2;
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
}
