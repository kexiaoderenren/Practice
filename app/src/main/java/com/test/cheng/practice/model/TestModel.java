package com.test.cheng.practice.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaokuncheng on 2016/12/5.
 */
public class TestModel implements Serializable{


    /**
     * status : 200
     * statusMsg : 成功
     * result : [{"submit":"1"}]
     */

    private int status;
    private String statusMsg;
    private List<ResultEntity> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public static class ResultEntity implements Serializable{
        /**
         * submit : 1
         */

        private String submit;

        public String getSubmit() {
            return submit;
        }

        public void setSubmit(String submit) {
            this.submit = submit;
        }
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "status=" + status +
                ", statusMsg='" + statusMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
