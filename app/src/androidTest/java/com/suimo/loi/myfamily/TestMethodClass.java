package com.suimo.loi.myfamily;

import android.util.Log;

import com.suimo.loi.myfamily.login.Model.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：loi on 2019-03-22 17:40
 * Emil：894900183@qq.com
 * - -
 */
public class TestMethodClass {


    public static void main(String[] args) {
        List a = new ArrayList();
        UserBean userBean = new UserBean("1","2","3","4","5");
        a.add(userBean);

        UserBean userBean1 = userBean;

        userBean1.setUserId("9627");
        a.add(userBean1);

        Log.e("userBean",userBean.getUserId());
        Log.e("userBean1",userBean1.getUserId());
    }

}
