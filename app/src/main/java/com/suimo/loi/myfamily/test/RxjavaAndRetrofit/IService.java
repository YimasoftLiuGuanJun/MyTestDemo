package com.suimo.loi.myfamily.test.RxjavaAndRetrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IService {
    //天气网址：http://www.weather.com.cn/data/sk/101010100.html
    @GET("data/sk/{location}")
    Call<WeatherBean> weatherInfo(@Path("location") String locationCode);
}

