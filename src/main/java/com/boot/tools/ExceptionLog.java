package com.boot.tools;

import java.io.*;

public class ExceptionLog {

    //生成文件路径
    private String path = "D:\\ExceptionLog\\";
    //文件路径+名称
    private String fileName = "ExceptionLogFile.txt";

    public void writeFile(String content) throws IOException {
        String filein = content+"\r\n";//新写入的行，换行
        String temp  = "";
        File file = new File(path + fileName);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;

        try {
            if (!file.exists()){        //文件不存在就创建
                file.createNewFile();
            }
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

    }

}
