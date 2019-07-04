package com.suimo.loi.myfamily.login.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.suimo.loi.myfamily.login.Model.UserBean;
import com.suimo.loi.myfamily.login.View.ILoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author：${loi} on 2019-04-29 09:44
 * Emil：894900183@qq.com
 * - -
 */

public class LoginPresenter{
    ILoginView iView;

    public LoginPresenter(ILoginView iView){
        this.iView = iView;
    }

    public void login(UserBean user){
        iView.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = new FormBody.Builder()
                        .add("sqlStr", "select * from UserInfo where loginName = '"+user.getName()+"'")
//                        .add("sqlStr", "select * from UserInfo")
                        .build();

                //        1.2. 同步GET请求
                OkHttpClient okHttpClient1 = new OkHttpClient();
                final Request request1 = new Request.Builder()
                        .url("http://47.101.136.5:3005/Service.asmx/ExecuteSql")
                        .post(requestBody)
                        .build();
                final Call call1 = okHttpClient1.newCall(request1);

                Response response = null;
                String onResponse= "";
                try {
                    response = call1.execute();
                    onResponse = response.body().string();//response.body().string() 只能调用一次!!!!!!!!!!!!!!!!!!!!!!1
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("onResponse返回的数据",onResponse);
                List<UserBean> userBeans = null;
                try {
                    userBeans = parseJsonWithJsonObject(onResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(userBeans!=null && userBeans.size()!=0){
                    if(userBeans.get(0).getPassWord().equals(user.getPassWord()))
                        iView.getContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iView.onPassWordCorrect();
                            }
                        });
                    else
                        iView.getContext().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iView.onPassWordError();
                            }
                        });
                }else
                    iView.getContext().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iView.onError();
                        }
                    });
            }
        }).start();
    }

    private List<UserBean> parseJsonWithJsonObject(String responseData) throws IOException {
        try{
//            String responseData=response.body().string();
            List<UserBean> userBeans = new ArrayList<>();
            JSONArray jsonArray=new JSONArray(responseData);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String userId=jsonObject.getString("userId");
                String name=jsonObject.getString("name");
                String age=jsonObject.getString("age");
                String passWord=jsonObject.getString("passWord");
                String loginName=jsonObject.getString("loginName");
                userBeans.add(new UserBean(userId,name,age,passWord,loginName));
            }
            return userBeans;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
