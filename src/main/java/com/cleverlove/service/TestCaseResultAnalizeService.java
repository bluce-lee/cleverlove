package com.cleverlove.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

public interface TestCaseResultAnalizeService extends FileService{

    default Set<String> getLogSet(String filename) {
        Set<String> dataSet = new HashSet<>();
        String content = getFileContent(filename);
        JSONArray jsonArray = JSONArray.parseArray(content);
        for (int i=0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String data = jsonObject.getString("data");
            if (data != null) {
                if (!dataSet.contains(data)) {
                    dataSet.add(data);
                }
            }
        }
        return dataSet;
    }

    default int getLogSetSize(String filename) {
        return getLogSet(filename).size();
    }
}
