package com.test.cheng.practice.test;

import java.util.List;

/**
 * Created by gaokuncheng on 2016/12/8.
 */
public class Student {
    private String name;//姓名
    private List<Course> coursesList;//所修的课程

    public Student(String name, List<Course> coursesList) {
        this.name = name;
        this.coursesList = coursesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
