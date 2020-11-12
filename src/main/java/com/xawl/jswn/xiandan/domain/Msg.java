package com.xawl.jswn.xiandan.domain;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;
    private String msg;
    private Map<String,Object> userInfo = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("登陆成功");
        return result;
    }
    public static Msg fail(String message){
        Msg result = new Msg();
        result.setCode(400);
        result.setMsg(message);
        return result;
    }

    public Msg add(String key,Object value){
        this.getUserInfo().put(key,value);
        return  this;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Object> userInfo) {
        this.userInfo = userInfo;
    }
}
