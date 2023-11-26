package com.itheima.service;
import com.itheima.pojo.Course;
import java.util.List;
public interface CourseService {
    /*
        查询所有课程
     */
    List<Course> getAllCourses();
}