package com.handscape.tailor.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/23
 * Time: 11:39
 */
public class NewJstFileAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        try {
            Project project = e.getData(PlatformDataKeys.PROJECT);
            VirtualFile virtualFile = e.getData(DataKeys.VIRTUAL_FILE);
            String filePath= virtualFile.getCanonicalPath();
            if(!virtualFile.isDirectory()){
                //是文件
                filePath=filePath.replace(virtualFile.getPresentableName(),"");
            }
            String newFileName = Messages.showInputDialog(project, "输入文件名称,默认创建对应js文件", "新建JST文件", Messages.getQuestionIcon());
            if (newFileName != null && !"null".equals(newFileName) && newFileName.length() != 0 ) {
                //这里可以执行文件名的判断
                if (true) {
                    //创建jst文件
                    String tojstpath= filePath + "/" + newFileName + ".jst";
                    boolean jstre=FileUtil.createIfDoesntExist(new File(tojstpath));
                    if(jstre){
                        InputStream fromjstpath= NewJstFileAction.class.getClassLoader().getResourceAsStream("tailor/config/projectTemplates/templatesjst.jst");
                        replaceFileContetn(fromjstpath, tojstpath, newFileName);
                    }
                    //创建对应js文件
                    String tojspath = filePath + "/" + newFileName + ".js";
                    boolean jsre = FileUtil.createIfDoesntExist(new File(tojstpath));
                    if (jsre) {
                        InputStream fromjspath = NewJstFileAction.class.getClassLoader().getResourceAsStream("tailor/config/projectTemplates/templatesjs.js");
                        replaceFileContetn(fromjspath, tojspath, newFileName);
                    }
                    //创建完成后刷新文件目录
                    virtualFile.refresh(true, true, new Runnable() {
                        @Override
                        public void run() {
                            VirtualFileManager.getInstance().syncRefresh();
                        }
                    });
                } else {
                    //错误返回内容给用户
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void replaceFileContetn(InputStream fromjstpath, String tojstpath,String newFileName){
        try {
            InputStreamReader read = new InputStreamReader(fromjstpath,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(read);
            FileOutputStream out=new FileOutputStream(tojstpath);
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
            String TxtBuff = null;
            while ((TxtBuff = bufferedReader.readLine()) != null) {
                TxtBuff = TxtBuff.replace("FILENAME", newFileName + ".js");
                writer.write(TxtBuff+"\r\n");
            }
            read.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
