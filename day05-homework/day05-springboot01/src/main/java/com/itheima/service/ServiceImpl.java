package com.itheima.service;

import com.itheima.dao.DaoImpl;
import com.itheima.pojo.Address;
import com.itheima.pojo.User;

import java.util.List;

public class ServiceImpl {
    public List<User> findAllUsers() {
        DaoImpl daoLevel = new DaoImpl();
        List<User> userList = daoLevel.findAllUsers();
        userList.stream().forEach(user -> {
            Address address = user.getAddress();
            String province = address.getProvince();
            String newProvince = "";
            if (province.equals("北京")) {
                newProvince = province + "市";
            } else {
                newProvince = province + "省";
            }

            String city = address.getCity();
            String newCity = "";
            if (city.equals("昌平")) {
                newCity = city + "区";
            } else {
                newCity = city + "市";
            }
            address.setProvince(newProvince);
            address.setCity(newCity);
        });
        return userList;
    }
}
