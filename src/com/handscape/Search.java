package com.handscape;

import java.io.*;
import java.util.regex.Pattern;

public class Search {
    public static void search(File folder, Pattern regex) {
        File[] files = folder.listFiles();
        if (files == null) {
            System.err.println("不能访问：" + folder.getAbsolutePath());
            return;
        }
        for(File file : files) {
            if(file.isDirectory()) {
                search(file, regex);
            } else {
                if (regex.matcher(file.getName()).matches()) {
                    replaceFile(file.getAbsolutePath());
                }
            }
        }
    }
    public static void search(String path, String regex) {
        search(new File(path), Pattern.compile(regex));
    }
    public static void main(String[] args) {
//        Search.search("E:\\000 work\\JAVA\\ECLIPSE\\yh\\WebRoot", ".*\\.css");
//        Search.search("D:\\", ".*\\.css");
    }
    
    public static void replaceFile(String path){
    	File file=new File(path);
        InputStreamReader read=null;
        String content = "";
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String TxtBuff = null;
            String indexline="";
            int i=0;
            while ((TxtBuff = bufferedReader.readLine()) != null) {
                content += TxtBuff+"\r\n";
                if(i==0){
                    indexline = TxtBuff;
                }
                i++;
            }
            try {
                read.close();
            } catch (Exception e) {
            }
            if(indexline.toLowerCase().indexOf("@charset")==-1){
                content="@charset 'utf-8';"+"\r\n"+content;
                FileOutputStream out = new FileOutputStream(path);
                OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
                try {
                    writer.write(content);
                    writer.close();
                    System.out.println("完成"+path);
                } catch (Exception e) {
                    System.out.println("失败" + path);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

