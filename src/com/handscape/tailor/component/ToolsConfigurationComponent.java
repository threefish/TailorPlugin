package com.handscape.tailor.component;

import com.handscape.tailor.form.ToolsConfiguration;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.NamedJDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/21
 * Time: 13:51
 */
public class ToolsConfigurationComponent implements ApplicationComponent, Configurable , NamedJDOMExternalizable {
    public ToolsConfigurationComponent() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
        System.out.println("ToolsConfigurationComponent is init");
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
        System.out.println("ToolsConfigurationComponent is dispose");
    }

    @NotNull
    public String getComponentName() {
        return "ToolsConfigurationComponent";
    }

    private ToolsConfiguration form;
    public String tailorServerPath = "请选择";


    public String getTailorServerPath() {
        return tailorServerPath;
    }

    public void setTailorServerPath(String tailorServerPath) {
        this.tailorServerPath = tailorServerPath;
    }

    @Nls
    @Override
    public String getDisplayName() {
        // Return name of configuration icon in Settings dialog
        return "Tialor";
    }

    public Icon getIcon() {
        return null;
    }


    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (form == null) {
            form = new ToolsConfiguration();
        }
        System.out.println("创建From");
        return form.getRootComponent();
    }

    @Override
    public boolean isModified() {
        return form != null && form.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        System.out.println("OK按钮或Apply按钮");
        if (form != null) {
            form.getData(this);
        }
    }

    @Override
    public void reset() {
        System.out.println("Cancel执行reset");
        if (form != null) {
            form.setData(this);
        }
    }

    @Override
    public void disposeUIResources() {
    //释放Form中涉及的各种资源
        System.out.println("释放Form中涉及的各种资源");
        form = null;
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        DefaultJDOMExternalizer.readExternal(this,element);
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        DefaultJDOMExternalizer.writeExternal(this, element);
    }

    @Override
    public String getExternalFileName() {
        return "tailorpro";
    }
}
