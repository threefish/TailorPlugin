package com.handscape.tailor.form;

import com.handscape.util.UtileBase64;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2917:23
 */

public class Base64Util {
    public JPanel rootPanel;
    private JTextArea textAreaSorue;
    private JButton bianma;
    private JButton jiema;


    public Base64Util() {
        bianma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaSorue.setText(UtileBase64.encode(textAreaSorue.getText().getBytes()));
            }
        });
        jiema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaSorue.setText(new String(UtileBase64.decode(textAreaSorue.getText())));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Base64Util");
        frame.setContentPane(new Base64Util().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
