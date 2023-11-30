package com.itheima.studentmanagementsystem;

import com.itheima.studentmanagementsystem.mapper.StudentMapper;
import com.itheima.studentmanagementsystem.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentManagementSystemApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void addStudentTest(){
        studentMapper.addStudent(new Student(5,"小明",23,'男'));
    }

    @Test
    public void queryAllStudentsTest(){
        List<Student> students = studentMapper.queryAllStudents();
        System.out.println(students);

    }

    @Test
    public void updateStudentTest(){
        studentMapper.updateStudent(new Student(5,"小明",20,'男'));
    }

    @Test
    public void deleteStudentTest(){
        studentMapper.deleteStudent(4);
    }



}
