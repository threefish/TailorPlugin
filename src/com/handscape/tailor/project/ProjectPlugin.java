package com.handscape.tailor.project;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/23
 * Time: 10:21
 */
public class ProjectPlugin implements ProjectComponent {
    public ProjectPlugin(Project project) {
        if(project.getName().equals("TailorProject")){
            String path = project.getBasePath() + "/.idea/.name";
            File file = new File(path);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                String pro = project.getBasePath();
                pro = pro.substring(pro.lastIndexOf("\\") + 1, pro.length());
                System.out.println("pro:" + pro);
                byte[] b1 = pro.getBytes();
                fos.write(b1);
                fos.flush();
                System.out.println("写入" + b1.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
        System.out.println("ProjectPlugin is initComponent");
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
        System.out.println("ProjectPlugin is disposeComponent");
    }

    @NotNull
    public String getComponentName() {
        return "ProjectPlugin";
    }

    public void projectOpened() {
        // called when project is opened
        System.out.println("ProjectPlugin: 正在打开项目");
    }

    public void projectClosed() {
        // called when project is being closed
        System.out.println("ProjectPlugin: 正在关闭项目");
    }
}
