package com.example.test.test.learnjava.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * flip 은 "뒤집다" 라는 뜻으로 알고있다.
 * 예를들어, Buffer에 put을 통해 일련의 데이터를 집어넣는 과정( writing ) 을 하다가 -> flip -> reading으로 가는거
 * 따라서, flip을 함과 동시에, 이제까지 buffer 에 들어온 크기로 limit을 설정하고, 처음부터 read할 테니 position은 0으로 갖다놓고..
 * 이런 느낌이다.
 * */
public class NioSimple {
    public static void main(String[] args) {
        NioSimple sample = new NioSimple();
        sample.basicWriteAndRead();
    }
    public void basicWriteAndRead(){
        String fileName = "D:\\test_file\\nio.txt";
        try{
            writeFile(fileName,"My first NIO sample");
            readFile(fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeFile(String fileName,String data)throws Exception{
        FileChannel channel = new FileOutputStream(fileName).getChannel();
        byte[] byteData = data.getBytes(); // String -> byte[] 변환
        ByteBuffer buffer = ByteBuffer.wrap(byteData); // ?!
        channel.write(buffer);
        channel.close();
    }
    
    public void readFile(String fileName) throws Exception{
        final FileChannel channel = new FileInputStream(fileName).getChannel();
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }
        channel.close();
    }
}
