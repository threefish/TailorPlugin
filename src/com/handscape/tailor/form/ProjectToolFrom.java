package com.handscape.tailor.form;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handscape.tailor.bean.WorkConfigurationBean;
import com.handscape.tailor.component.ToolsConfigurationComponent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/3014:58
 */

public class ProjectToolFrom {
    private JList list;
    public JPanel rootConment;
    private JTextField workName;
    private JTextField baseUrl;
    private JTextField defaultUrl;
    private JTextField workSpace;
    private JTextField port;
    private JRadioButton base64ModeOn;
    private JRadioButton base64ModeOff;
    private JRadioButton debugModeOn;
    private JRadioButton debugModeOff;
    private JButton addbutton;
    private JButton savebutton;
    private JButton updatebutton;
    private JButton resetbutton;
    private JTextField workid;
    private JButton delbutton;
    private JButton choseWorkPath;
    private Application application = ApplicationManager.getApplication();
    private ToolsConfigurationComponent toolsConfigurationComponent = application.getComponent(ToolsConfigurationComponent.class);
    private List<WorkConfigurationBean> listbean = null;
    private Gson gson = new Gson();
    private DefaultListModel listModel = new DefaultListModel();



    public ProjectToolFrom() {
        rootConment.addAncestorListener(new AncestorListener() {
            /**
             * 初始化页面方法
             * @param event
             */
            @Override
            public void ancestorAdded(AncestorEvent event) {
                initloadlist();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        /**
         * list 点击事件
         */
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updatebutton.setEnabled(true);
                savebutton.setEnabled(true);
                delbutton.setEnabled(true);
                WorkConfigurationBean bean = listbean.get(list.getSelectedIndex());
                workName.setText(bean.getWorkName());
                baseUrl.setText(bean.getBaseUrl());
                defaultUrl.setText(bean.getDefaultUrl());
                workSpace.setText(bean.getWorkSpace());
                port.setText(bean.getPort());
                workid.setText(bean.getId());
                if (bean.getBase64Mode().equals("on")) {
                    base64ModeOn.setSelected(true);
                    base64ModeOff.setSelected(false);
                } else {
                    base64ModeOn.setSelected(false);
                    base64ModeOff.setSelected(true);
                }
                if (bean.getDebugMode().equals("on")) {
                    debugModeOn.setSelected(true);
                    debugModeOff.setSelected(false);
                } else {
                    debugModeOn.setSelected(false);
                    debugModeOff.setSelected(true);
                }
            }
        });
        /**
         * 写入tailor配置
         */
        updatebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTailorServerFile();
            }
        });

        /**
         * 保存配置
         */
        savebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<WorkConfigurationBean> listwork = new ArrayList<WorkConfigurationBean>();
                    for (int i = 0; i < listbean.size(); i++) {
                        WorkConfigurationBean bean = listbean.get(i);
                        if (bean.getId().equals(workid.getText())) {
                            bean.setWorkName(workName.getText());
                            bean.setWorkSpace(workSpace.getText());
                            bean.setBaseUrl(baseUrl.getText());
                            bean.setDefaultUrl(defaultUrl.getText());
                            bean.setPort(port.getText());
                            bean.setId(workid.getText());
                            if (base64ModeOn.isSelected()) {
                                bean.setBase64Mode("on");
                            } else {
                                bean.setBase64Mode("off");
                            }
                            if (debugModeOn.isSelected()) {
                                bean.setDebugMode("on");
                            } else {
                                bean.setDebugMode("off");
                            }
                        }
                        listwork.add(bean);
                    }
                    if(saveJsonFile(listwork)){
                        Messages.showMessageDialog("修改成功", "消息", Messages.getInformationIcon());
                    }else{
                        Messages.showMessageDialog("修改失败", "消息", Messages.getWarningIcon());
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        base64ModeOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base64ModeOff.setSelected(false);
            }
        });
        base64ModeOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base64ModeOn.setSelected(false);
            }
        });
        debugModeOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugModeOff.setSelected(false);
            }
        });
        debugModeOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugModeOn.setSelected(false);
            }
        });
        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workName.setText("");
                baseUrl.setText("");
                defaultUrl.setText("");
                workSpace.setText("");
                workid.setText("");
                port.setText("");
                base64ModeOn.setSelected(false);
                base64ModeOff.setSelected(true);
                debugModeOn.setSelected(false);
                debugModeOff.setSelected(true);
                updatebutton.setEnabled(false);
                savebutton.setEnabled(false);
            }
        });
        /**
         * 新增
         */
        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatebutton.setEnabled(true);
                savebutton.setEnabled(true);
                boolean falg= false;
                for(WorkConfigurationBean bean:listbean){
                    if(bean.getWorkName().equals(workName.getText())){
                        falg=true;
                    }
                }
                if (workName.getText().trim().length() == 0) {
                    Messages.showMessageDialog("请输入项目名称", "消息", Messages.getQuestionIcon());
                }else
                if(falg){
                    Messages.showMessageDialog("项目名称重复", "消息", Messages.getQuestionIcon());
                }else {
                    WorkConfigurationBean bean = new WorkConfigurationBean();
                    bean.setBase64Mode((base64ModeOn.isSelected() ? "on" : "off"));
                    bean.setDebugMode((debugModeOn.isSelected() ? "on" : "off"));
                    bean.setId(UUID.randomUUID().toString());
                    bean.setWorkSpace(workSpace.getText());
                    bean.setPort(port.getText());
                    bean.setBaseUrl(baseUrl.getText());
                    bean.setDefaultUrl(defaultUrl.getText());
                    bean.setWorkName(workName.getText());
                    listbean.add(bean);
                    if(saveJsonFile(listbean)){
                        Messages.showMessageDialog("添加成功", "消息", Messages.getQuestionIcon());
                    }else{
                        Messages.showMessageDialog("添加失败", "消息", Messages.getWarningIcon());
                    }
                }

            }
        });
        delbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(rootConment, "是否删除选中项目", "删除提示", JOptionPane.YES_NO_OPTION);
                if (response == 0) {
                    resetbutton.doClick();
                    updatebutton.setEnabled(false);
                    savebutton.setEnabled(false);
                    List<WorkConfigurationBean> newworklist=new ArrayList<WorkConfigurationBean>();
                    WorkConfigurationBean workConfigurationBean = listbean.get(list.getSelectedIndex());
                    for (WorkConfigurationBean bean : listbean) {
                        if (!bean.getId().equals(workConfigurationBean.getId())) {
                            newworklist.add(bean);
                        }
                    }
                    if(saveJsonFile(newworklist)){
                        Messages.showMessageDialog("删除成功", "消息", Messages.getQuestionIcon());
                    }else{
                        Messages.showMessageDialog("删除失败", "消息", Messages.getQuestionIcon());
                    }
                } else if (response == 1) {
//                    System.out.println("您按下了第Cancel按钮");
                }

            }
        });
        choseWorkPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(1);// 设定只能选择到文件夹
                int returnVal = chooser.showOpenDialog(rootConment);// 此句是打开文件选择器界面的触发语句
                if (returnVal == 1) {
                    return;
                } else {
                    File f = chooser.getSelectedFile();// f为选择到的目录
                    System.out.println(f.getAbsolutePath());
                    String[] s= f.getAbsolutePath().toString().split("\\\\");
                    String realPath = "";
                    for(int i=0;i<s.length;i++){
                        if(i==0){
                            realPath=s[i];
                        }else{
                            realPath+="\\\\"+s[i];
                        }
                    }
                    workSpace.setText(realPath);
                }
            }
        });

    }


    /**
     * 保存json文件
     * @param workConfigurationBeanList
     * @return
     */
    public  boolean saveJsonFile(List<WorkConfigurationBean> workConfigurationBeanList){
        boolean status=false;
        String jsonFieldPath = toolsConfigurationComponent.getTailorServerPath() + "\\tailortool.json";
        if (!toolsConfigurationComponent.getTailorServerPath().equals("请选择")) {
            FileOutputStream out = null;
            OutputStreamWriter writer = null;
            try {
                out = new FileOutputStream(jsonFieldPath);
                writer = new OutputStreamWriter(out, "utf-8");
                String content = gson.toJson(workConfigurationBeanList);
                writer.write(content);
                writer.flush();
                loadList(workConfigurationBeanList);
                status=true;
            } catch (Exception ex) {
            } finally {
                try {
                    writer.close();
                    out.close();
                } catch (Exception ex) {}
            }
        } else {
            //暂无配置文件
            Messages.showMessageDialog("没有设置TailorServer目录，请先在Setting中配置", "消息", Messages.getQuestionIcon());
        }
        return status;
    }

    /**
     * 加载jslit数据
     *
     * @param listbean
     */
    public void loadList(List<WorkConfigurationBean> listbean) {
        listModel.removeAllElements();
        for (int i = 0; i < listbean.size(); i++) {
            WorkConfigurationBean bean = listbean.get(i);
            listModel.add(i, bean.getWorkName());
        }
        list.setModel(listModel);
    }

    /**
     * 写入tailorserver文件
     */
    public void writeTailorServerFile() {
        String tailorServerPath = toolsConfigurationComponent.getTailorServerPath()+"\\WEB-INF\\classes\\TailorServer.properties";
        if (!toolsConfigurationComponent.getTailorServerPath().equals("请选择")) {
            String content = "";
            File file = new File(tailorServerPath);
            InputStreamReader read = null;
            try {
                read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String TxtBuff = null;
                while ((TxtBuff = bufferedReader.readLine()) != null) {
                    if (TxtBuff.trim().indexOf("#") == 0) {
                        content += TxtBuff + "\r\n";
                    } else {
                        String key = TxtBuff.trim().split("=")[0];
                        if (key.equals("AcceptorPort")) {
                            content += key + "=" + port.getText() + "\r\n";
                        } else if (key.equals("TailorWorkSpace")) {
                            content += key + "=" + workSpace.getText() + "\r\n";
                        } else if (key.equals("TailorEngineBaseUrl")) {
                            content += key + "=" + baseUrl.getText() + "\r\n";
                        } else if (key.equals("TailorTargetDefaultUrl")) {
                            content += key + "=" + defaultUrl.getText() + "\r\n";
                        } else if (key.equals("DebugMode")) {
                            content += key + "=" + (debugModeOn.isSelected() ? "on" : "off") + "\r\n";
                        } else if (key.equals("TargetUrlEncodeBase64Mode")) {
                            content += key + "=" + (base64ModeOn.isSelected() ? "on" : "off") + "\r\n";
                        } else {
                            content += TxtBuff + "\r\n";
                        }
                    }
                }
                try {
                    read.close();
                } catch (IOException ex) {
                }
                FileOutputStream out = null;
                OutputStreamWriter writer = null;
                try {
                    out = new FileOutputStream(tailorServerPath);
                    writer = new OutputStreamWriter(out, "utf-8");
                    writer.write(content);
                    writer.flush();
                } catch (IOException ee) {
                } finally {
                    try {
                        writer.close();
                    } catch (Exception ex) {
                    }
                }
                Messages.showMessageDialog("写入成功，你现在可以启动Tailor服务了", "消息", Messages.getQuestionIcon());
            } catch (IOException e1) {
                Messages.showMessageDialog("写入失败!" + e1.getMessage(), "消息", Messages.getWarningIcon());
            }
        } else {
            Messages.showMessageDialog("没有设置TailorServer目录，请先在Setting中配置", "消息", Messages.getQuestionIcon());
        }
    }

    /**
     * 初始化加载list
     */
    public void initloadlist() {
        String jsonpath = toolsConfigurationComponent.getTailorServerPath()+"\\tailortool.json";
        if (!jsonpath.equals("请选择")) {
            File file = new File(jsonpath);
            InputStreamReader read = null;
            try {
                read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String TxtBuff = null;
                String str = "";
                while ((TxtBuff = bufferedReader.readLine()) != null) {
                    str += TxtBuff;
                }

                Type listType = new TypeToken<List<WorkConfigurationBean>>() {
                }.getType();
                listbean = gson.fromJson(str, listType);
                loadList(listbean);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    read.close();
                } catch (Exception e) {
                }
            }
        } else {
            //暂无配置文件
            Messages.showMessageDialog("没有设置TailorServer目录，请先在Setting中配置", "消息", Messages.getQuestionIcon());
        }

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ProjectToolFrom");
        frame.setTitle("多项目配置管理工具");
        frame.setContentPane(new ProjectToolFrom().rootConment);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
