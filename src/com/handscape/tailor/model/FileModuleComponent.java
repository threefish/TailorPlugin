package com.handscape.tailor.model;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/23
 * Time: 11:59
 */
public class FileModuleComponent implements ModuleComponent {
    Module module;
    public FileModuleComponent(Module module) {
        this.module = module;
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }


//    public void createFileFromTemplate() throws Exception {
//        ModuleRootManager rootModel = ModuleRootManager.getInstance(module);
//        JOptionPane.showMessageDialog(null, rootModel.getModule().getName());
//        final Module module = rootModel.getModule();
//        final Project project = module.getProject();
//        Properties properties = FileTemplateManager.getInstance().getDefaultProperties();
//        final PsiDirectory directory = PsiManager.getInstance(project).findDirectory(rootModel.getSourceRoots()[0]);
//        FileTemplate template = FileTemplateManager.getInstance().getJ2eeTemplate("test.java");
//        FileTemplateUtil.createFromTemplate(template, "test.java", properties, directory);
//        System.out.println("xxx");
//    }
    @NotNull
    public String getComponentName() {
        return "FileModuleComponent";
    }

    public void projectOpened() {
        // called when project is opened
    }

    public void projectClosed() {
        // called when project is being closed
    }

    public void moduleAdded() {
        // Invoked when the module corresponding to this component instance has been completely
        // loaded and added to the project.
    }
}
