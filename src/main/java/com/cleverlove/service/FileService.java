package com.cleverlove.service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public interface FileService {

    default Map<String, File> getListFiles(String path) {
        File temp = new File(path);
        File[] files = temp.listFiles();
        Map<String, File> fileMap = new HashMap<>();
        for (File file : files) {
            fileMap.put(file.getName(),file);
        }
        return fileMap;
    }

    default String getFileContent(File file) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        try {
//            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            int lines = 0;
            while ((temp = bufferedReader.readLine()) != null) {
                lines++;
                stringBuilder.append(lines + "\t" + temp + "\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            return new String(stringBuilder.toString().getBytes("utf-8"), "gb2312");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return stringBuilder.toString();
    }

    default String getFileContent(String filename) {
        filename = filename.substring(0,filename.lastIndexOf('.'));
        filename = "res/data/result/" + filename + ".json";
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String temp;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp + "\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
