package com.handscape.tailor.component;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/22
 * Time: 13:04
 */
public class TialorMenuPluginRegistration implements ApplicationComponent {
    // Returns the component name (any unique string value).
    @NotNull
    public String getComponentName() {
        return "TialorMenuPlugin";
    }

    // If you register the MyPluginRegistration class in the <application-components> section of
    // the plugin.xml file, this method is called on IDEA start-up.
    public void initComponent() {
//        ActionManager am = ActionManager.getInstance();
//        TextBoxes action = new TextBoxes();
//        // Passes an instance of your custom TextBoxes class to the registerAction method of the ActionManager class.
//        am.registerAction("TialorMenuPlugin", action);
//        // Gets an instance of the WindowMenu action group.
//        DefaultActionGroup windowM = (DefaultActionGroup) am.getAction("WindowMenu");
        // Adds a separator and a new menu command to the WindowMenu group on the main menu.
//        windowM.addSeparator();
//        windowM.add(action);
    }

    // Disposes system resources.
    public void disposeComponent() {
    }
}