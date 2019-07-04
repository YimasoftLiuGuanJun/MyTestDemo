package com.suimo.loi.myfamily.http;

import com.suimo.loi.myfamily.GankConfig;
import com.suimo.loi.myfamily.mvp.model.GankData;
import com.suimo.loi.myfamily.mvp.model.MainData;
import com.suimo.loi.myfamily.mvp.model.MeiziData;
import com.suimo.loi.myfamily.mvp.model.RestVideoData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bob on 17-5-1.
 * Retrofit的API接口，定义了向服务器请求数据的方法
 */

public interface GankRetrofit {

        //获取DailActivity界面的妹子图
        @GET("data/福利/" + GankConfig.GANK_SIZE + "/{page}")
        Observable<MeiziData> getMeiziData(@Path("page") int page);

        //获取DailyActivity的视频文字简介
        @GET("data/休息视频/" + GankConfig.MEIZI_SIZE + "/{page}")
        Observable<RestVideoData> getRestViideoData(@Path("page") int page);

        //获取GankActivity界面的内容
        @GET("day/{year}/{month}/{day}")
        Observable<GankData> getDailyData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

        //获取MainActivity界面的数据
        @GET("data/{type}/" + GankConfig.GANK_SIZE + "/{page}")
        Observable<MainData> getMainData(@Path("type") String type, @Path("page") int page);

}
