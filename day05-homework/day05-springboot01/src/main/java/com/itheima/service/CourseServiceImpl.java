package com.itheima.service;

import com.itheima.pojo.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        Course c1 = new Course("Java");
        Course c2 = new Course("Python");
        Course c3 = new Course("C++");
        Collections.addAll(list,c1,c2,c3);
        return list;
    }
}
