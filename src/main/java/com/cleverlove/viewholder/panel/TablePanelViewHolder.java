package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class TablePanelViewHolder extends BaseViewHolder {

    private String[] titles = { "学号","姓名","性别","成绩" };
    private DefaultTableModel tableModel = null;
    private JTable jTable = null;
    private JScrollPane jScrollPane = null;

    public TablePanelViewHolder(Object object) {
        super(object);
    }

    public void init() {
        String[][] datas = {};
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("打开文件");
		jFileChooser.setCurrentDirectory(new File("./res/"));
        int flag = jFileChooser.showOpenDialog(null);
        if (flag == jFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "导入文件成功");
            File file = jFileChooser.getSelectedFile();
        }
        tableModel = new DefaultTableModel(datas, titles);
        jTable = new JTable(tableModel);

//        for (Student student : students) {
//            String[] data = {student.getId(), student.getName(), student.getSex(), student.getGrade()};
//            tableModel.addRow(data);
//        }
        jScrollPane= new JScrollPane(jTable);
    }
}
