package com.hannstar.shiro.spring.boot.shiro.demo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author jiangxinjun
 * @date 2019-04-13
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    /**
     * 返回码
     */
    private int ret;

    /**
     * 返回信息，可直接对用户公开
     */
    private String msg;

    /**
     * 调试信息，不直接对用户公开
     */
    private String debug;

    /**
     * 返回的数据
     */
    private T model;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
