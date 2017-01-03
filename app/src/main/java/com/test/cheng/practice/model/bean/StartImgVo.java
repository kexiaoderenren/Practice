package com.test.cheng.practice.model.bean;

import java.io.Serializable;

/**
 * Created by keixaoderenren on 2017/1/3.
 */
public class StartImgVo implements Serializable {


    /**
     * text : © 桃子
     * img : https://pic3.zhimg.com/v2-beb690851729623283314060e56bb506_xxdpi.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "StartImgVo{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
