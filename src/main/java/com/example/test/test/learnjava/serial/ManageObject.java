package com.example.test.test.learnjava.serial;

import java.io.*;

public class ManageObject {
    public static void main(String[] args) {
        final ManageObject manager = new ManageObject();
        StringBuilder sb = new StringBuilder("");
        sb.append(File.separator).append("test_file").append(File.separator).append("myObject"); // 이렇게 하면 test_file 이라는 파일로 생성되어버림.
        String fullPath = sb.toString();
        final SerialDTO dto = new SerialDTO("JAVABook", 1, true, 100);
        manager.saveObject(fullPath,dto);
        manager.loadObject(fullPath);
    }
    public void saveObject(String fullPath,SerialDTO dto){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(fullPath);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dto); // 경로(디렉토리)를 "생성" 해 주지는 않는다
            System.out.println("Write success");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(oos!=null){
                try{
                    oos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if(fos != null){
            try{
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void loadObject(String fullPath){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(fullPath);
            ois = new ObjectInputStream(fis);

            final Object obj = ois.readObject();
            final SerialDTO dto = (SerialDTO) obj;
            System.out.println(dto);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(ois!=null){
                try{
                    ois.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try{
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
