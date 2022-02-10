package com.example.test.test.learnjava.nio;

import java.nio.IntBuffer;

/**
 * buffer 에서 위치가 이동하는 모습을 살펴보자
 * */
public class NioBufferSample {
    public static final int INIT_SIZE = 1024;
    public static void main(String[] args) {
        final NioBufferSample sample = new NioBufferSample();
//        sample.checkBuffer();
        sample.checkBufferStatus();
    }
    public void checkBuffer(){
        try{
            final IntBuffer buffer = IntBuffer.allocate(INIT_SIZE);
            for(int l = 0; l<100;l++) buffer.put(l);
            System.out.println("buffer.capacity() = " + buffer.capacity()); // INIT_SIZE
            System.out.println("buffer.limit() = " + buffer.limit()); // INIT_SIZE <== limit의 position을 별도로 지정하지 않았으므로, 기본 크기가 출력된다.
            System.out.println("buffer.position() = " + buffer.position()); // 데이터 추가후 buffer 의 position은 100이 된다.
            buffer.flip();
            System.out.println("Buffer flipped!"); // flip 메소드 호출
            System.out.println("buffer.limit() = " + buffer.limit()); // 100 <== limit 은 100이되고, position은 0 이된다.
            System.out.println("buffer.position() = " + buffer.position()); // 0
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkBufferStatus(){
        try{
            final IntBuffer buffer = IntBuffer.allocate(INIT_SIZE);
            buffer.get(); // position 이 1 증가한다
            printBufferStatus("get", buffer); // 1
            buffer.mark();
            printBufferStatus("mark",buffer); // 현재 position 을 저장해둠 -> reset 해 올 거임
            buffer.get();
            printBufferStatus("get", buffer); // 2
            buffer.reset();
            printBufferStatus("reset",buffer); // 현재 position은 이전에 mark해두었던 1로 돌아감
            buffer.rewind();
            printBufferStatus("rewind", buffer); // 현재 position을 0으로 이동
            buffer.get();
            printBufferStatus("get", buffer); // 1
            buffer.get();
            printBufferStatus("get", buffer); // 2
            buffer.clear();
            printBufferStatus("clear",buffer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void printBufferStatus(String job, IntBuffer buffer){
        System.out.print("Buffer "+job+" !!!!");
        System.out.format(" Buffer position = %d remaining= %d limit = %d\n", buffer.position(), buffer.remaining(), buffer.limit());

    }


}
