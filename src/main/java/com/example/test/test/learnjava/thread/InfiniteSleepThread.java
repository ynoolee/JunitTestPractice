package com.example.test.test.learnjava.thread;

public class InfiniteSleepThread extends Thread{
    private final long sleepTime;

    public InfiniteSleepThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try{
            while(true){

                Thread.sleep(sleepTime);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
