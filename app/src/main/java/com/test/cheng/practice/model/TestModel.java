package com.test.cheng.practice.model;

/**
 * Created by gaokuncheng on 2016/12/5.
 */
public class TestModel {

    private String submit;

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "submit='" + submit + '\'' +
                '}';
    }
}
