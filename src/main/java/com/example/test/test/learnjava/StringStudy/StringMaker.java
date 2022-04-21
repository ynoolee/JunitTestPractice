package com.example.test.test.learnjava.StringStudy;

public class StringMaker {

    private final int LOOP_COUNT;

    public StringMaker(int loop) {
        LOOP_COUNT = loop;
    }

    public String makeStringUsingBuilder(String op){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i< LOOP_COUNT;i++){
            sb.append(op);
        }
        return sb.toString();
    }

    public String makeStringUsingBuffer(String op){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< LOOP_COUNT;i++){
            sb.append(op);
        }
        return sb.toString();
    }

    public String makeStringByString(String op){
        String res = "";
        for (int i = 0; i< LOOP_COUNT;i++){
            res += op;
        }
        return res;
    }
}
