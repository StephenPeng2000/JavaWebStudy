package com.itheima.studentmanagementsystem.mapper;

import com.itheima.studentmanagementsystem.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    void addStudent(Student student);

    void deleteStudent(Integer id);

    void updateStudent(Student student);

    List<Student> queryAllStudents();
}
