package com.suimo.loi.myfamily.test.RxjavaAndRetrofit;

import android.graphics.Bitmap;

import com.suimo.loi.myfamily.GankConfig;
import com.suimo.loi.myfamily.login.Model.UserBean;
import com.suimo.loi.myfamily.mvp.model.MeiziData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyLoginService {
    @GET("ExecuteSql" )
    Call<List<UserBean>> login(
            @Query("sqlStr") String sql
    );

    @GET("ExecuteSql" )
    Observable<Response<List<UserBean>>> login1(
            @Query("sqlStr") String sql
    );

    @GET("ExecuteSql" )
    Observable<List<UserBean>> login2(
            @Query("sqlStr") String sql
    );

    @GET("Pictures/{location}")
    Call<Bitmap> getPictures(@Path("location") String locationCode);
}
