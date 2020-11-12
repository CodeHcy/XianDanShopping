package com.xawl.jswn.xiandan.domain;

/**
 * @author hcy
 * @create 2020-11-12-12:25
 * @flag Keep on running
 */
public class GetUserInfo {
    private Integer code;
    private String msg;
    private BUser userInfo;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BUser userInfo) {
        this.userInfo = userInfo;
    }
}
