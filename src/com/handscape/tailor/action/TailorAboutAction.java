package com.handscape.tailor.action;

import com.handscape.tailor.form.TailorAboutForm;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: HuangChuan
 * Date: 2015/2/12
 * Time: 12:48
 */
public class TailorAboutAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        JFrame frame = new JFrame("TailorAboutForm");
        frame.setSize(400, 200);
        frame.setTitle("关于Tailor插件");
        frame.setContentPane(new TailorAboutForm().rootComent);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
