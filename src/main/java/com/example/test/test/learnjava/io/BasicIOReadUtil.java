package com.example.test.test.learnjava.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BasicIOReadUtil {
  /**
   * read()
   * 문자 한개를 read 해 온다.
   * read 한 글자를 리턴한다.
   * */
  public static ArrayList readCharStream(String fileName) throws Exception{
    ArrayList<StringBuffer> list = new ArrayList<StringBuffer>();
    FileReader fr = null;
    try{
      fr = new FileReader(fileName);
      int data = 0;
      // 한 줄 씩 데이터를 담을 StringBuffer 를 생성
      StringBuffer sb = new StringBuffer();
      while((data = fr.read())!= -1){ // Reads a single character -> return the character read or -1 if the end of the stream has been reached
        // 줄 바꿈 글자에 도달 -> 한 줄 의 데이터를 읽은 경우
        // 해당 StringBuffer 를 List 에다가 담는다
        if(data == '\n' || data == '\r'){
          list.add(sb);
          sb = new StringBuffer();
        } else {
          sb.append((char)data);
        }
      }
    } catch (IOException e){
      System.err.println(e.getMessage());
    } catch (Exception e){
      System.err.println(e.getMessage());
    } finally {
      if (fr != null) fr.close();
    }
    return list;
  }
  /**
   * read(char[] buffer)
   * chracter 들을 buffer array 로 읽어온다. 따라서 인자로는 destination buffer가 온다.
   * 읽은 character 의 수를 리턴한다. 내부적으로 최대 해당 버퍼의 길이만큼 read 하게 된다.
   * */
  public static String readCharStreamWithBuffer(String fileName) throws Exception{
    StringBuffer sb = new StringBuffer();
    FileReader fr = null;
    try{
      fr = new FileReader(fileName);
      int bufferSize = 1024 *1024;
      char[] readBuffer = new char[bufferSize];
      int resultSize = 0;
      while((resultSize = fr.read(readBuffer)) != -1) {
        if (resultSize == bufferSize){
          sb.append(readBuffer);
        } else {
          // 읽어온 데이터 < bufferSize
          // 1. 파일의 끝에 달한 상황
          // 2. bufferSize 만큼을 append 할 경우 , 쓰레기 데이터(이전의 read로 남아있는 데이터) 까지 append 된다.
          // 2-1. 따라서, read 크기만큼 , 즉 resultSize 만큼만을 append 해 주도록 한다
          for(int loop = 0; loop < resultSize; loop++){
            sb.append(readBuffer[loop]);
          }
        }
      }
    } catch (IOException e){
      System.err.println(e.getMessage());
    } catch (Exception e){
      System.err.println(e.getMessage());
    } finally {
      if (fr != null) fr.close();
    }
    return sb.toString();
  }

  public static ArrayList<String> readBufferedReader(String fileName) throws IOException{
    ArrayList<String> list = new ArrayList<>();
    BufferedReader br = null;
    try{
      br = new BufferedReader(new FileReader(fileName));
      String data;
      while ((data = br.readLine()) != null) list.add(data);
    } catch (Exception e){
      System.out.println(e.getMessage());
    } finally {
      if (br != null) br.close();
    }
    return list;
  }

}
