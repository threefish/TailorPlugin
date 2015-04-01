package com.handscape;

import java.io.*;
import java.util.regex.Pattern;

public class SearchLuan {
    public static void search(File folder, Pattern regex,String luanStr, String newStr) {
        File[] files = folder.listFiles();
        if (files == null) {
            System.err.println("不能访问：" + folder.getAbsolutePath());
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                search(file, regex, luanStr,newStr);
            } else {
                if (regex.matcher(file.getName()).matches()) {
                    replaceFile(file.getAbsolutePath(), luanStr,newStr);
                }
            }
        }
    }

    public static void search(String path, String regex,String luanStr,String newStr) {
        search(new File(path), Pattern.compile(regex),luanStr, newStr);
    }



    public static void replaceFile(String path, String luanStr,String newStr) {
        File file = new File(path);
        InputStreamReader read = null;
        String content = "";
        try {
            read = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            String TxtBuff = null;
            boolean luanFlag = false;
            while ((TxtBuff = bufferedReader.readLine()) != null) {
                if (TxtBuff.indexOf(luanStr) > -1) {
                    luanFlag = true;
                    TxtBuff = TxtBuff.replaceAll(luanStr, newStr);
                }
                content += TxtBuff + "\r\n";
            }
            try {
                read.close();
            } catch (Exception e) {
            }
            if (luanFlag) {
                FileOutputStream out = new FileOutputStream(path);
                OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
                try {
                    writer.write(content);
                    writer.close();
                    System.out.println("完成" + path);
                } catch (Exception e) {
                    System.out.println("失败" + path);
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        SearchLuan.search("E:\\000 work\\JAVA\\ECLIPSE\\yh\\WebRoot\\core\\funcs\\news\\show", ".*\\.jsp", "新闻","信息");
    }
}

