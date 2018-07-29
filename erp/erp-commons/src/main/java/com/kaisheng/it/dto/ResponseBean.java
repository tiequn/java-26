package com.kaisheng.it.dto;

/**
 * @author guojiabang
 * @date 2018/7/27 0027
 */
public class ResponseBean{

    private static final String RESPONSEBEAN_STATE_SUCCESS = "success";
    private static final String RESPONSEBEAN_STATE_ERROR = "error";

    private String state;
    private Object data;
    private String message;

    public static ResponseBean success() {
        ResponseBean resppnseBean = new ResponseBean();
        resppnseBean.setState(RESPONSEBEAN_STATE_SUCCESS);
        return resppnseBean;
    }

    public static ResponseBean success(Object obj) {
        ResponseBean resppnseBean = new ResponseBean();
        resppnseBean.setState(RESPONSEBEAN_STATE_SUCCESS);
        resppnseBean.setData(obj);
        return resppnseBean;
    }

    public static ResponseBean error(String message) {
        ResponseBean resppnseBean = new ResponseBean();
        resppnseBean.setState(RESPONSEBEAN_STATE_ERROR);
        resppnseBean.setMessage(message);
        return resppnseBean;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
