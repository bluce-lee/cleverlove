package com.cleverlove.view.panel;

import com.cleverlove.base.BaseFileSelectPanel;
import com.cleverlove.base.SingletonObservable;
import com.cleverlove.entity.Msg;
import com.cleverlove.service.FileServiceImpl;
import com.cleverlove.viewholder.panel.CodeFileSelectPanelViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Map;

public class CodeFileSelectPanel extends BaseFileSelectPanel {

    public CodeFileSelectPanelViewHolder codeFileSelectPanelViewHolder;

    public CodeFileSelectPanel() {
        this.setBackground(new Color(218, 255, 198, 255));
        listmodel = new DefaultListModel();
        fileService = new FileServiceImpl();
        codeFileSelectPanelViewHolder = new CodeFileSelectPanelViewHolder(this);
        codeFileSelectPanelViewHolder.jList.setModel(listmodel);
        new MThread("res/data/code").start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == codeFileSelectPanelViewHolder.jButton) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("打开文件");
            jFileChooser.setCurrentDirectory(new File("./res/data/"));
            int flag = jFileChooser.showOpenDialog(null);
            if (flag == jFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "导入文件成功");
                File file = jFileChooser.getSelectedFile();
                String content = fileService.getFileContent(file);
                Msg msg = new Msg();
                msg.setType(1);
                msg.getContent().put(Msg.MSG_SINGATURE,file.getName());
                SingletonObservable.getSingletonObservable().notifyObservers(msg);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String filename = (String) codeFileSelectPanelViewHolder.jList.getSelectedValue();
        String content = fileService.getFileContent(fileMap.get(filename));
        Msg msg = new Msg();
        msg.setType(1);
        msg.getContent().put(Msg.MSG_SINGATURE, filename);
        msg.getContent().put(Msg.MSG_CONTENT, content);
        SingletonObservable.getSingletonObservable().notifyObservers(msg);
    }

    private class MThread extends Thread {
        private String path;
        public MThread(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            fileMap = fileService.getListFiles(path);
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                listmodel.addElement(entry.getKey());
            }
        }
    }
}
