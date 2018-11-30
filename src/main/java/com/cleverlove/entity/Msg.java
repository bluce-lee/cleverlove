package com.cleverlove.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Msg {

    public static final String MSG_SINGATURE = "singature";
    public static final String MSG_CONTENT = "content";

    private int type;
    private Map<String, String> content;


    private List<String> fileNameList;

    public Msg() {
        content = new HashMap<>();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "type=" + type +
                ", content=" + content +
                ", fileNameList=" + fileNameList +
                '}';
    }
}
