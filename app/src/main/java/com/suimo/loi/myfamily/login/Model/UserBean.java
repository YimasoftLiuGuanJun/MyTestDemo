package com.suimo.loi.myfamily.login.Model;

/**
 * Author：loi on 2019-01-18 16:53
 * Emil：894900183@qq.com
 * - -
 */
public class UserBean {
    String userId;
    String name;
    String age;
    String passWord;
    String loginName;

    public UserBean(String userId, String name, String age, String passWord, String loginName) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.passWord = passWord;
        this.loginName = loginName;
    }

    public UserBean(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

}
