package com.suimo.loi.myfamily.test.RxjavaAndRetrofit;

public class MyLoginPostBodyBean {
    String sqlStr;
    public MyLoginPostBodyBean(String userId) {
        this.sqlStr = "select * from UserInfo where userId = "+userId;
    }
}
