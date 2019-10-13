package com.suimo.loi.myfamily.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface ApiMySerer {
//    http://47.101.136.5:3006/gif1.gif
    String Url="http://47.101.136.5:3006/";
    @Streaming
    @GET("gif1.gif")
    Observable<ResponseBody> downloadimage();
}