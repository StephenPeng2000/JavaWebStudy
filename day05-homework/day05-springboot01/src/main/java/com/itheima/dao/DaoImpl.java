package com.itheima.dao;

import com.itheima.pojo.User;
import com.itheima.utils.XmlParserUtils;

import java.util.List;

public class DaoImpl {
    public List<User> findAllUsers() {
        String file = this.getClass().getClassLoader().getResource("user.xml").getFile();
        List<User> userList = XmlParserUtils.parse(file);
        return userList;
    }
}
