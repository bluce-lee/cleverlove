package com.cleverlove.view.panel;

import com.cleverlove.base.BaseFileSelectPanel;
import com.cleverlove.base.SingletonObservable;
import com.cleverlove.entity.Msg;
import com.cleverlove.service.FileServiceImpl;
import com.cleverlove.viewholder.panel.TestCaseCodeFileSelectPanelViewHolder;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

public class TestCaseCodeFileSelectPanel extends BaseFileSelectPanel implements ListSelectionListener {

    private TestCaseCodeFileSelectPanelViewHolder testCaseCodeFileSelectPanelViewHolder;

    public TestCaseCodeFileSelectPanel() {
        this.setBackground(new Color(218, 255, 198, 255));
        listmodel = new DefaultListModel();
        fileService = new FileServiceImpl();
        testCaseCodeFileSelectPanelViewHolder = new TestCaseCodeFileSelectPanelViewHolder(this);
        testCaseCodeFileSelectPanelViewHolder.jList.setModel(listmodel);
        new MThread("res/data/testcase").start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == testCaseCodeFileSelectPanelViewHolder.jButton) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("打开文件");
            jFileChooser.setCurrentDirectory(new File("./res/data/"));
            int flag = jFileChooser.showOpenDialog(null);
            if (flag == jFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(null, "导入文件成功");
                File file = jFileChooser.getSelectedFile();
                String content = fileService.getFileContent(file);
                Msg msg = new Msg();
                msg.setType(2);
                msg.getContent().put(Msg.MSG_SINGATURE, file.getName());
                msg.getContent().put(Msg.MSG_CONTENT, content);
                SingletonObservable.getSingletonObservable().notifyObservers(msg);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<String> selectedValues = (List<String>) testCaseCodeFileSelectPanelViewHolder.jList.getSelectedValuesList();
        String content = fileService.getFileContent( fileMap.get( selectedValues.get(0) ) );
        Msg msg = new Msg();
        msg.setType(2);
        msg.getContent().put(Msg.MSG_SINGATURE, selectedValues.get(0));
        msg.getContent().put(Msg.MSG_CONTENT, content);
        msg.setFileNameList(selectedValues);
        SingletonObservable.getSingletonObservable().notifyObservers(msg);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
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
