package com.itheima.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
    获取请求参数的类
    @RestController 让springboot框架识别
    @RestController 等于：
        1.@Controller(将当前类RequestParamController对象放到springIOC容器中)
            +
        2.@ResponseBody作用：如果修饰类，将类中所有的方法响应给前端，如果修饰方法就是将某个方法返回值响应给前端
        如果某个方法返回是实体类对象或者集合，那么该注解可以将其转换为json数据
 */
@RestController
public class RequestParamController {
    /**
     * 1.获取简单参数的原始方式
     */
//    @RequestMapping("/simpleParam")
//    public String simpleParam(HttpServletRequest request){//request对象是由tomcat创建的，将请求行 头 体数据全部封装到该对象中
//        //1.获取请求参数username的值
//        String username = request.getParameter("username");
//        //2.获取请求参数age的值
//        String age = request.getParameter("age");
//        System.out.println(username+"---"+age);
//        //3.响应数据
//        return "获取简单参数的原始方式";
//    }
    /*
        现在使用的方式接收简单参数
     */
//    @RequestMapping("/simpleParam")
//    public String simpleParam(String username,int age){
////    public String simpleParam(String name,Integer age){
//        System.out.println(username+"---"+age);
//        //null --- 18 因为前端提交的请求参数是：http://localhost:8080/simpleParam?username=柳岩&age=18
//        // 用户名的key是username，这里形参是name，因此无法获取数据，形参名必须是前端提交的请求参数的key一致
//        //System.out.println(name+"---"+age);
//        //3.响应数据
//        return "获取简单参数的原始方式";
//    }



    /*
        使用数组接收传递的数据
        注意：要求前端提交的请求参数的key要和方法形参名一致
        http://localhost:8080/arrayParam?hobby=game&hobby=java===》String[] hobby
     */
 /*   @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        //输出数组
        System.out.println(Arrays.toString(hobby));
        //3.响应数据
        return "数组";
    }*/


    /*
       获取前端提交的时间数据
       需要使用注解@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 指定时间格式，pattern的属性值要和前端
       提交的时间格式一致
     */
  /*  @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        //输出数组
        System.out.println(updateTime);
        //3.响应数据
        return "集合";
    }*/




}
