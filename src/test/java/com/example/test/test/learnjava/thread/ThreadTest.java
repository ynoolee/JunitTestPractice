package com.example.test.test.learnjava.thread;

import org.junit.jupiter.api.*;

public class ThreadTest {
    private long sleepThreadSleepTime;
    private long currentSleepTime;
    private long currentJoinTime;
    private long infiniteSleepThreadTime;

    private SleepThread A;
    private FiniteJoinThread B;
    private InfiniteSleepThread C;

    @BeforeEach
    public void initTime(){
        sleepThreadSleepTime = 1000;
        infiniteSleepThreadTime = 700;
        currentJoinTime = 500;
        currentSleepTime = 500;

        A = new SleepThread(sleepThreadSleepTime);
        B = new FiniteJoinThread(A);
        C = new InfiniteSleepThread(infiniteSleepThreadTime);
    }
    @AfterEach
    public void clean(){
        A.interrupt();
        C.interrupt();
    }

    @Test
    @DisplayName("일정시간 동안만 Sleeping 하는 thread(A) 에 대해 join() 을 호출한 스레드(B)의 상태를 확인한다")
    public void checkJoinCallingThreadState(){
        // when
        B.start();
        try {
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){
        }
        // then
        Assertions.assertEquals(Thread.State.WAITING,B.getState());
    }
    @Test
    @DisplayName("sleep,wait,join 상태인 스레드(B)에 대해 join()을 호출한 스레드(B)에서 join() 이후 연산이 실행될 때 B 스레드의 상태 ")
    public void interruptJoinCallingThread(){
        // when
        B.start();
        try {
            Thread.sleep(currentSleepTime);
            B.join();
        }catch (InterruptedException e){
        }
        // then  : join() 이 리턴되어왔다는 것은, B 의 termination 을 의미하기 때문
        Assertions.assertEquals(Thread.State.TERMINATED,B.getState());

    }

    @Test
    @DisplayName("무한 루프를 돌며 sleep 을 호출하는 중인 스레드 상태를 확인")
    public void checkStateOfSleepingThread(){
        //when
        C.start();
        // then
        try{
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){}
        Assertions.assertEquals(Thread.State.TIMED_WAITING,C.getState());
    }
    @Test
    @DisplayName("Timed_waiting 상태인 스레드(C) 에 D 스레드에서 join(100) 을 호출 한 후, interrupt는 걸지 않고 C 스레드의 상태를 확인")
    public void checkJustAfterJoin(){
        //when
        C.start();
        // then
        try{
            Thread.sleep(currentSleepTime);
            C.join(currentJoinTime);
        }catch (InterruptedException e){}
        // A 의 completion 이 일어나지 않았더라도 join(millisec) 가 리턴되어오기 때문
        Assertions.assertEquals(Thread.State.TIMED_WAITING,C.getState());
    }


    @Test
    @DisplayName("무한루프를 돌며 sleep 중인 스레드(C) 에 interrupt를 건다")
    public void interruptInfiniteSleepThread(){

        // when
        try{
            C.start();
            Thread.sleep(currentSleepTime);
            C.interrupt();
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){}
        //then
        Assertions.assertEquals(Thread.State.TERMINATED,C.getState());
    }

}
