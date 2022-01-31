package com.example.test.test.learnjava.thread;

public class FiniteJoinThread extends Thread{

    private final Thread thread;

    public FiniteJoinThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.start();
        try {
            this.sleep(100);
            thread.join(); // thread 가 무한으로 sleep 하는 애면 , 이 함수호출은 리턴되어오지를 않음
        }catch(InterruptedException e){

        }
    }

}
