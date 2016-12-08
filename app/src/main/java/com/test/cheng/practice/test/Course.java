package com.test.cheng.practice.test;

import java.io.Serializable;

/**
 * Created by gaokuncheng on 2016/12/8.
 */
public class Course implements Serializable {

    public Course(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private String name;//课程名
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
