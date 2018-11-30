package com.cleverlove.base;

import com.cleverlove.service.FileService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public abstract class BaseFileSelectPanel extends JPanel implements BaseMouseListener, ActionListener {

    public FileService fileService;
    public DefaultListModel listmodel;
    public Map<String, File> fileMap;
}
