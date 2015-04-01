package com.handscape.tailor.form;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/2/1212:31
 */

public class TailorAboutForm {
    private JTextArea mess;
    public JPanel rootComent;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TailorAboutForm");
        frame.setContentPane(new TailorAboutForm().rootComent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
