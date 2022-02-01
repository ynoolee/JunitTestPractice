package com.example.test.test.learnjava.thread;
/**
 * join 은 어떤 스레드가 중지상태가 될 때 까지 대기라고 하는데
 * "중지" ?? 그냥 로직 끝나면 terminated되는 것 아닌가??
 *
 * */

public class SleepThread extends Thread{
    long sleepTime;

    public SleepThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(sleepTime);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
