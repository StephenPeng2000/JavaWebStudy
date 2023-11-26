package com.itheima;

import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test01 {
	//装配StudentService实现类对象
    @Autowired
    StudentService studentService;

    @Test
    public void test01(){
        //创建学生对象
        //使用学生对象调用printCourse方法
        Student s1 = new Student();
        s1.setName("小明");
        s1.setSelectedCourses(studentService.getAllCourses());
        s1.printCourse();
       
    }
}