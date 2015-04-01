package com.handscape.tailor.form;

import com.google.gson.Gson;
import com.handscape.tailor.bean.WorkConfigurationBean;
import com.handscape.tailor.component.ToolsConfigurationComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2113:33
 */

public class ToolsConfiguration  {
    private JPanel rootComponent;
    private JLabel tailorServerLable;
    private JFormattedTextField tailorServerPath;
    private JButton choseFileButton;
//    private JFormattedTextField jsonFieldPath;
//    private JButton choseJsonFileButton;
//    private JLabel jsonLable;

    public ToolsConfiguration() {
        tailorServerLable.setLabelFor(tailorServerPath);
//        jsonLable.setLabelFor(jsonFieldPath);
        //选择TailorServer配置文件
        choseFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                JFileChooser chooser = new JFileChooser();
//                //定义文件过滤器
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("properties文件", "properties");
//                chooser.setFileFilter(filter);
//                int returnVal = chooser.showOpenDialog(rootComponent);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    tailorServerPath.setText(chooser.getSelectedFile().getAbsolutePath());
//                }

                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(1);// 设定只能选择到文件夹
                int returnVal = chooser.showOpenDialog(rootComponent);// 此句是打开文件选择器界面的触发语句
                if (returnVal == 1) {
                    return;
                } else {
                    File f = chooser.getSelectedFile();// f为选择到的目录
                    tailorServerPath.setText(f.getAbsolutePath());
                    String jsonpath = f.getAbsolutePath() + "\\tailortool.json";
                    File jsonfile=new File(jsonpath);
                    try {
                        jsonfile.createNewFile();
                        List<WorkConfigurationBean> workConfigurationBeanList=new ArrayList<WorkConfigurationBean>();
                        WorkConfigurationBean bean=new WorkConfigurationBean();
                        bean.setId(UUID.randomUUID().toString());
                        bean.setDefaultUrl("http://www.ifeng.com/");
                        bean.setWorkName("凤凰网参考例子");
                        bean.setBaseUrl("http://127.0.0.1:1306/tailor");
                        bean.setPort("1306");
                        bean.setBase64Mode("off");
                        bean.setDebugMode("off");
                        bean.setWorkSpace("请选择具体的TailorJWorkSpace工作目录");
                        workConfigurationBeanList.add(bean);
                        FileOutputStream out = null;
                        OutputStreamWriter writer = null;
                        try {
                            Gson gson=new Gson();
                            out = new FileOutputStream(jsonfile);
                            writer = new OutputStreamWriter(out, "utf-8");
                            String content = gson.toJson(workConfigurationBeanList);
                            writer.write(content);
                            writer.flush();
                        } catch (Exception ex) {
                        } finally {
                            try {
                                writer.close();
                                out.close();
                            } catch (Exception ex) {
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }
        });
//        //选择JSON配置文件
//        choseJsonFileButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JFileChooser chooser = new JFileChooser();
//                //定义文件过滤器
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("json文件", "json");
//                chooser.setFileFilter(filter);
//                int returnVal = chooser.showOpenDialog(rootComponent);
//                if (returnVal == JFileChooser.APPROVE_OPTION) {
//                    jsonFieldPath.setText(chooser.getSelectedFile().getAbsolutePath());
//                }
//            }
//        });
    }

    public JComponent getRootComponent() {
        return rootComponent;
    }

    public void setData(ToolsConfigurationComponent data) {
        tailorServerPath.setText(data.getTailorServerPath());
//        jsonFieldPath.setText(data.getJsonFieldPath());
    }

    public void getData(ToolsConfigurationComponent data) {
        data.setTailorServerPath(tailorServerPath.getText());
//        data.setJsonFieldPath(jsonFieldPath.getText());
    }

    public boolean isModified(ToolsConfigurationComponent data) {
        boolean falg= tailorServerPath.getText() != null ? !tailorServerPath.getText().equals(data.getTailorServerPath()) : data.getTailorServerPath() != null;
//        boolean falg2= jsonFieldPath.getText() != null ? !jsonFieldPath.getText().equals(data.getJsonFieldPath()) : data.getJsonFieldPath() != null;
        if(falg){
//        if(falg||falg2){
          return  true;
        }else{
          return  false;
        }
    }


}
