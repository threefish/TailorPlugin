package com.handscape.tailor.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/1/29
 * Time: 17:22
 */
public class Base64Action extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        JFrame frame = new JFrame("Base64Util");
        frame.setSize(400,200);
        frame.setContentPane(new com.handscape.tailor.form.Base64Util().rootPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
