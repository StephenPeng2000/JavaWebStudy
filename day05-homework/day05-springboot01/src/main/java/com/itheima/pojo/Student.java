package com.itheima.pojo;

import java.util.List;

/*
    学生类
 */
public class Student {
    //学生姓名
    private String name;
    //已选课程列表
    private List<Course> selectedCourses;

    // 构造器、getter和setter方法

    public Student(String name, List<Course> selectedCourses) {
        this.name = name;
        this.selectedCourses = selectedCourses;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Course> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public void printCourse() {
        System.out.println(name + "选择的课程是："+selectedCourses);
    }
}