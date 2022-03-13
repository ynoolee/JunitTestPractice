package com.example.test.test.learnjava.thread;

import org.junit.jupiter.api.*;

public class ThreadTest {
    private long sleepThreadSleepTime;
    private long currentSleepTime;
    private long currentJoinTime;
    private long infiniteSleepThreadTime;
    private long waitForStart;

    private SleepThread A;
    private InfiniteJoinThread B;
    private InfiniteSleepThread C;
    private InfiniteWorkingThread D;

    @BeforeEach
    public void initTime(){
        sleepThreadSleepTime = 1000;
        infiniteSleepThreadTime = 700;
        currentJoinTime = 500;
        currentSleepTime = 500;
        waitForStart = 10;

        A = new SleepThread(sleepThreadSleepTime);
        B = new InfiniteJoinThread(A);
        C = new InfiniteSleepThread(infiniteSleepThreadTime);
        D = new InfiniteWorkingThread();
    }
    @AfterEach
    public void clean(){
        A.interrupt();
        C.interrupt();
        D.toggleStopFlag();
    }
    @Test
    public void 블락상태가아닌스레드에대한_인터럽트는_스레드를_멈추지_못한다(){
        D.start();
        try {
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Assertions.assertEquals(Thread.State.RUNNABLE, D.getState());
        D.interrupt();
        Assertions.assertTrue(D.isInterrupted());
        Assertions.assertEquals(Thread.State.RUNNABLE, D.getState());
    }
    /**
     * interrupt 를 호출한다고 즉시 TERMINATED 되는 것은 아니다
     * 어떤 스레드를 즉각적으로 멈추게 할 수는 없다.
     * Interrupting 이라는 것은 기본적으로 스레드에게 " 너 interrupt 되었어 " 라는 메시지를 보내는 것이다.
     * 또, 특히나 JAVA에서 interrupt를 호출하는 것은 그저 실행중이던 스레드의 flag를 세팅하는 것이다.
     * 그 스레드에서 interrupt status flag 가 세팅 되었음을 발견(?, check) 해야 한다
     */
    /**
     * Status flag 에 대하여
     * interrupt() 호출은 interrupt status flag 를 세팅한다.
     * interrupted 스레드 측 에서 Thread.interrupted()를 호출하여 interrupt 여부에 대한 체크를 할 수 있는데, 이는 interrupt status 를 clear 한다.
     * */

    @Test
    public void 블락상태인_스레드에대한_인터럽트는_스레드를_멈춘다(){
        A.start();
        try {
            Thread.sleep(waitForStart);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Assertions.assertEquals(Thread.State.TIMED_WAITING, A.getState());
        A.interrupt();
        // interrupt 를 호출한다고 즉시 TERMINATED 되는 것은 아니기 때문에 일정 시간 이후, 스레드의 상태를 확인하자
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Assertions.assertEquals(Thread.State.TERMINATED, A.getState()); // 해당 스레드의 sleep 타임보다 한참 못 미치는 시간이나 TERMINATE되었음을 확인
    }

    @Test
    @DisplayName("일정시간 동안만 Sleeping 하는 thread(A) 에 대해 join() 을 호출한 스레드(B)의 join 직후 상태를 확인한다")
    public void checkJoinCallingThreadState(){
        // when
        B.start();
        try {
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // then : A 는 일정시간 동안만 sleep 하지만, 아직 완료되지 않았음 -> B는 waiting 상태
        Assertions.assertEquals(Thread.State.WAITING,B.getState());
    }
    @Test
    @DisplayName("sleep,wait,join 상태인 스레드(B)에 대해 join()을 호출한 스레드(D)에서 join() 이후 연산이 실행될 때 B 스레드의 상태 ")
    public void interruptJoinCallingThread(){
        // when
        B.start();
        try {
            Thread.sleep(currentSleepTime);
            B.join();
        }catch (InterruptedException e){
            e.printStackTrace();
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
        }catch (InterruptedException e){
            e.printStackTrace();
        }
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
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // then
        // A 의 completion 이 일어나지 않았더라도 join(millisec) 가 리턴되어오기 때문
        // 이는 join() 을 호출하지 않았을 때랑 똑같은 결과인 것.
        Assertions.assertEquals(Thread.State.TIMED_WAITING,C.getState());
    }


    @Test
    @DisplayName("무한루프를 돌며 sleep 중인 스레드(C) 에 interrupt 를 건다")
    public void interruptInfiniteSleepThread(){

        // when
        try{
            C.start();
            Thread.sleep(currentSleepTime);
            C.interrupt();
            Thread.sleep(currentSleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //then : 오해가 있었음. 이미 thread C 는 sleep 으로 인해 TIMED_WAITING 상태 -> 여기에 굳이 join 을 호출하지 않고 interrupt()를 걸어줘도 되는 거였음. 따라서 당연히 이 테스트는 성공한다.
        Assertions.assertEquals(Thread.State.TERMINATED,C.getState());
    }

}
