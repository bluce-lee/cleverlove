package com.cleverlove.service;

import com.cleverlove.entity.User;

import java.io.*;
import java.util.Properties;

public interface UserService {

    default boolean login(User user) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("res/data/user.properties");
        } catch (FileNotFoundException e) {
            System.out.println("加载数据失败");
            e.printStackTrace();
        }
        Properties properties = new Properties();
        String name;
        String password;
        try {
            properties.load(inputStream);
            inputStream.close();
            name = properties.getProperty("username");
            password = properties.getProperty("password");
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("加载数据失败");
            e.printStackTrace();
        }
        return false;
    }

    default boolean cacheUserInfo(User user) {
        Properties properties = new Properties();
        properties.setProperty("username", user.getName());
        properties.setProperty("password", user.getPassword());
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("res/cache/user.properties");
            properties.store(outputStream, "usercache");
            outputStream.close();
            return true;
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
            return false;
        }
    }

    default boolean clearCacheUserInfo() {
        File file = new File("res/cache/user.properties");
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }


    default public User loadCache() {
        InputStream inputStream = null;
        Properties properties = new Properties();
        String name;
        String password;
        try {
            inputStream = new FileInputStream("res/cache/user.properties");
            properties.load(inputStream);
            inputStream.close();
            name = properties.getProperty("username");
            password = properties.getProperty("password");
            if (!name.equals("") && !password.equals("")) {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                return user;
            }
        } catch (IOException e) {
            System.out.println("加载数据失败");
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
