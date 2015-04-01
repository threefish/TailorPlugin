package com.handscape.tailor.action;

import com.handscape.tailor.form.ProjectToolFrom;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/30
 * Time: 14:57
 */
public class ProjectToolAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        JFrame frame = new JFrame("ProjectToolFrom");
        frame.setSize(650, 400);
        frame.setContentPane(new ProjectToolFrom().rootConment);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
