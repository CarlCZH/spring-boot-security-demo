package com.hui.security.supportBean;

/**
 * @Author: CarlChen
 * @Despriction: 返回的简单json格式信息
 * @Date: Create in 17:08 2019\2\8 0008
 */
public class SimpleJsonMsg {

    private Object context;

    public SimpleJsonMsg(Object context) {
        this.context = context;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
}
