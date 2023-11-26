package com.itheima.web;

import com.itheima.pojo.Address;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.ServiceImpl;
import com.itheima.utils.XmlParserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    获取请求参数的类
    @RestController 让springboot框架识别
    @RestController 等于：
        1.@Controller(将当前类RequestParamController对象放到springIOC容器中)
            +
        2.@ResponseBody作用：如果修饰类，将类中所有的方法响应给前端，如果修饰方法就是将某个方法返回值响应给前端
        如果某个方法返回是实体类对象或者集合，那么该注解可以将其转换为json数据
 */
//@RestController
@Controller
@ResponseBody
public class ResponseTestController {

    /*
        1.案例一： 统一响应结果
        需求：统一后端给前端的响应结果
        给前端统一响应类型
                json={
                    "code": 1,
                    "msg": "查询地址成功",
                    "data": [
                        {
                            "province": "浙江省",
                            "city": "杭州"
                        },
                        {
                            "province": "江苏省",
                            "city": "南京"
                        }
                    ]
            }
        说明：
            1.在真实的项目开发中，无论是增删改查的那种方法，我们都会定义一个统一的返回结果，在这个返回结果中，包含一下信息：
                A. 当前请求是成功，还是失败。
                B. 当前给页面的提示信息。
                C. 返回的数据。
            2.对于上述的这些数据呢，我们一般都会定义在一个实体类Result中，实体类中有三个成员变量：
                A.code :响应码，1 代表成功; 0 代表失败
                B.msg:响应成功还是失败信息，描述字符串
                C.data:返回的数据，存储查询的数据
           3.统一响应的实体类Result已经定义完毕
           4.请求路径是：/list
           5.响应给前端如下数据：
                 ArrayList<Address> list = new ArrayList<>();
                    Address address = new Address();
                    address.setCity("杭州");
                    address.setProvince("浙江省");

                    Address address2 = new Address();
                    address2.setCity("南京");
                    address2.setProvince("江苏省");

                    Collections.addAll(list, address, address2);
              将list转换为Result并返回
     */
    //TODO:你的代码
    @RequestMapping("/list")
    public Result responseList(){
        ArrayList<Address> list = new ArrayList<>();
        Address address = new Address();
        address.setCity("杭州");
        address.setProvince("浙江省");

        Address address2 = new Address();
        address2.setCity("南京");
        address2.setProvince("江苏省");

        Collections.addAll(list, address, address2);
        return Result.success(list);
    }

    /*
        2.案例二： 统一响应结果
        需求：统一后端给前端的响应结果
        给前端统一响应类型
              {
                    "code": 1,
                    "msg": "查询成功",
                    "data": {
                        "province": "浙江省",
                        "city": "杭州"
            }
        说明：
           1.请求路径是：/getAddress
           2.响应给前端如下数据：
                 Address address = new Address();
                address.setCity("杭州");
                address.setProvince("浙江省");
              将address转换为Result并返回
     */
    //TODO:你的代码
    @RequestMapping("/address")
    public Result getAddress(){
        Address address = new Address();
        address.setCity("杭州");
        address.setProvince("浙江省");
        return Result.success(address);
    }

       /*
        3.案例三： 获取用户数据，返回统一响应结果，在页面渲染展示
        需求：需求：加载并解析xml文件，完成省、市数据转换，并完成页面展示。
        说明：
           1.如果获取的是省或者直辖市需要在后面加：省/市。如果获取的是市或者区，需要在后面加 市/区
           2.解析xml的dom4j依赖已经导入到pom.xml中
           3.要解析的user.xml已经放到resources目录下面了
           4.解析XML文件的工具类，已经准备好，无需自己实现，
            已经放到包  com.itheima.utils下面了
           5.前端页面资源，已经准备好，放在  src/main/resources 下的static目录下。
     */

    //定义方法查询后端数据响应给前端
    //前端请求路径axios.get('/listUser')，后端的路径在 @RequestMapping("/listUser")指定
    @RequestMapping("/listUser")
    public Result findAllUsers() {
        //TODO:你的代码，最后响应的不是null，是Result对象
        ServiceImpl serviceLevel = new ServiceImpl();
        List<User> userList = serviceLevel.findAllUsers();
        return Result.success(userList);
//        String file = this.getClass().getClassLoader().getResource("user.xml").getFile();
//        List<User> userList = XmlParserUtils.parse(file);

//        userList.stream().forEach(user -> {
//            Address address = user.getAddress();
//            String province = address.getProvince();
//            String newProvince = "";
//            if(province.equals("北京")){
//                newProvince =  province + "市";
//            }else {
//                newProvince =  province + "省";
//            }
//
//            String city = address.getCity();
//            String newCity = "";
//            if(city.equals("昌平")){
//                newCity = city + "区";
//            }else {
//                newCity = city + "市";
//            }
//            address.setProvince(newProvince);
//            address.setCity(newCity);
//        });
    }
}
