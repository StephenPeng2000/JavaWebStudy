package com.itheima.web;

import com.itheima.pojo.Address;
import com.itheima.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class ResponseController {
    //定义方法响应数据给前端 respStr
    @RequestMapping("/respStr")
//    @ResponseBody
    public String respStr(){
        System.out.println("哈哈...");
        //响应字符串
        return "呵呵";
    }
    /*
        {
            "province": "浙江省",
            "city": "杭州"
        }
     */
//    @RequestMapping("/getAddress")
////    @ResponseBody
//    public Address getAddress(){
//        Address address = new Address();
//        address.setCity("杭州");
//        address.setProvince("浙江省");
//        //响应实体类对象
//        return address;
//    }

    /*
              [
            {
                "province": "浙江省",
                "city": "杭州"
            },
            {
                "province": "江苏省",
                "city": "南京"
            }
        ]
    */
//    @RequestMapping("/list")
////    @ResponseBody
//    public List<Address> list(){
//        //创建集合对象
//        ArrayList<Address> list = new ArrayList<>();
//        Address address = new Address();
//        address.setCity("杭州");
//        address.setProvince("浙江省");
//
//        Address address2 = new Address();
//        address2.setCity("南京");
//        address2.setProvince("江苏省");
//
//        Collections.addAll(list, address, address2);
//        //响应集合对象
//        return list;
//    }




    /*
                {
                    "code": 1,
                    "msg": "查询成功",
                    "data": {
                        "province": "浙江省",
                        "city": "杭州"
            }
        }
     */
    @RequestMapping("/getAddress")
    public Result getAddress(){
        Address address = new Address();
        address.setCity("杭州");
        address.setProvince("浙江省");

        //将地址对象放到Result
        Result result = new Result(1, "查询成功", address);
        //响应实体类对象
        return result;
    }


}
