package com.itheima.web;

import com.itheima.pojo.User;
import org.springframework.web.bind.annotation.*;

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
@RestController
public class RequestParamTestController {
    /**
     * 1.请求：简单参数和实体参数
     * 案例一： 参数名不一致
     * 说明：
     *  1）如果方法形参名称与请求参数名称不一致，可以使用 @RequestParam 完成映射。
     *  2）如果请求参数名为 username，controller方法形参使用name进行接收，是不能够直接封装的，
     *      需要在方法形参前面加上 @RequestParam 然后通过value属性执行请求参数名，从而完成映射，
     *  3）@RequestParam(value = "name",required = false) String username
     *        required属性默认值是true表示前端传递数据必须传递，否则报错，如果设置为false,那么前端可以不传递数据
     *  4）请求路径是：/simpleParam
     */
    //TODO:书写你的代码
    @RequestMapping("/test1.1")
    public String simpleParam1(String username, int age){
        System.out.println(username + "-----" + age);
        return "简单参数传递成功";
    }

    @RequestMapping("/test1.2")
    public String simpleParam2(@RequestParam(value = "name",required = false)String username,int age){
        System.out.println(username + "-----" + age);
        return "简单参数传递成功";
    }


    /**
     * 2.请求：简单参数和实体参数
     * 案例二： 实体参数_简单实体对象
     * 如果请求参数比较多，通过上述的方式一个参数一个参数的接收，会比较繁琐。
     * 此时，我们可以考虑将请求参数封装到一个 pojo 对象中。
     * 要想完成数据封装，需要遵守如下规则：请求参数名与POJO的属性名相同
     *
     * 说明：
     *  1）User实体类已经在pojo包下定义完毕，直接使用
     *  2）请求路径是：/simplePojo
     */
    //TODO:书写你的代码
    @RequestMapping("/test2")
    public String simpleParamObject(User user){
        System.out.println(user);
        return "简单实体对象传递成功";
        //传递user里面的address时，由于address也是个对象，其中包含了province和city，我们在传递的时候可以用address.province，或者json格式。
    }



    /**
     * 3.请求：简单参数和实体参数
     * 案例三： 复杂实体对象
     * 复杂实体对象的封装，需要遵守如下规则：
     * 请求参数名与形参对象属性名相同，按照对象层次结构关系即可接收嵌套POJO属性参数。
     *
     * 说明：
     *  1）请求路径是：/complexPojo
     *  2）Address和User实体类已经定义完毕
     *  3）如果前端发送的是复杂的实体对象，要求提交的key要和实体类的成员变量名都一致：
     *   public class User {
     *             //成员变量
     *             private String name;
     *             private Integer age;
     *             //定义自定义类型
     *             private Address address;
     *          }
     *
     *          public class Address {
     *             //成员变量
     *             private String province;//省份
     *             private String city;//城市
     *          }
     *
     *   前端提交数据：http://localhost:8080/complexPojo?name=Tom&age=10&address.province=beijing&address.city=beijing
     *
     */
    //TODO:书写你的代码

    /**
     * 4.需求：Controller方法的形参是单列集合(例如List)，接收前端提交的多个请求参数
     * 集合参数：请求参数名与形参集合对象名相同且请求参数为多个，@RequestParam 绑定参数关系
     *
     * 说明：
     *  1） http://localhost:8080/arrayParam?hobby=sing&hobby=java===》@RequestParam List<String> hobby
     *         如果前端提交的请求参数想向集合中存储，那么需要使用注解@RequestParam 修饰，这样才可以封装数据到集合中
     *  2）要求前端提交的请求参数的key要和方法形参名一致
     *  3）总结：
     *   @RequestParam注解作用：
     *      1.解决前端提交请求参数的key和后端方法的形参名不一致@RequestParam(value="key")
     *      2.解决后端方法形参是集合封装数据问题
     *  4）请求路径是：/listParam
     */
    //TODO:书写你的代码
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "集合参数传递成功";
    }


    /**
     * 5.前端传递JSON格式的数据，后端使用实体类接收
     * 需求：前端向后端传递如下JSON格式的数据：
     * {
     *  "name":"Tom",
     *  "age":18,
     *  "address":{
     *      "province":"shanghai",
     *      "city":"shanghai"
     *  }
     * }
     * 后端需要接收并输出控制台。
     * 说明：
     *  1） 其实呢，在前后端进行交互时，如果是比较复杂的参数，前后端通过会使用JSON格式的数据进行传输。
     *      而传递json格式的参数，服务端，在Controller中，我们通常会使用实体类进行封装
     *  2）JSON数据键名与形参对象属性名相同，定义POJO类型形参即可接收参数。需要使用 @RequestBody 标识。
     *  3）Address User 实体类已经定义完毕
     *  4）请求路径是：/jsonParam
     */
    //TODO:书写你的代码
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "json传递成功";
    }


    /**
     * 6.获取前端提交的路径参数的数据
     * 需求：前端传递数据的格式是路径参数，后端接收路径参数并输出到控制台
     * 说明：
     *  1） 路径参数：就是参数作为路径的一部分 http://localhost:8080/path/1  1既是参数又是路径一部分
     *  2） 路径参数要求{标识符}的标识符必须和方法的形参名一致，这样大括号接收到的前端数据就会封装到形参中
     *  3） 前端：http://localhost:8080/path/1  ===》/path/{id}  ===》id接收的是1，赋值给形参id
     *  4）如果接收的是路径参数必须使用注解@PathVariable修饰形参
     *  5）请求路径是：/path/{id}==》 前端：http://localhost:8080/path/1
     */
    //TODO:书写你的代码
    @RequestMapping("/path/{id}")
    public String path1(@PathVariable Integer id){
        System.out.println(id);
        return "路径传递成功";
    }


    /**
     * 7.获取前端提交的路径参数的数据
     * 需求：前端传递数据的格式是路径参数，后端接收路径参数并输出到控制台
     * 说明：
     *  1） 路径参数：就是参数作为路径的一部分 http://localhost:8080/path/1  1既是参数又是路径一部分
     *  2） 路径参数要求{标识符}的标识符必须和方法的形参名一致，这样大括号接收到的前端数据就会封装到形参中
     *  3） 前端：http://localhost:8080/path/1/Tom  ===》/path/{uid}/{name}  ===》uid接收的是1，name接收的是Tom
     *  4）如果接收的是路径参数必须使用注解@PathVariable修饰形参
     *  5）请求路径是：/path/{uid}/{name}==》 前端：http://localhost:8080/path/1/Tom
     */
    //TODO:书写你的代码
    @RequestMapping("/path/{id}/{name}")
    public String path2(@PathVariable Integer id, @PathVariable String name){
        System.out.println(id + "-----" + name);
        return "路径传递成功";
    }
}
