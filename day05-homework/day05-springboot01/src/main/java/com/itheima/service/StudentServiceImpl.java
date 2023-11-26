package com.itheima.service;

import com.itheima.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    CourseService courseService;
    @Override
    public List<Course> getAllCourses() {
        List<Course> list = courseService.getAllCourses();
        return list;
    }
}
