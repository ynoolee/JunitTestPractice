package com.example.test.test.learnjava.thread;

public class InfiniteWorkingThread extends Thread{
  private boolean stopFlag = false;
  private long idle = 0L;

  @Override
  public void run() {
    while(!stopFlag){
      idle++;
    }
  }

  public void toggleStopFlag(){
    stopFlag = stopFlag == true ? false : true;
  }
}
