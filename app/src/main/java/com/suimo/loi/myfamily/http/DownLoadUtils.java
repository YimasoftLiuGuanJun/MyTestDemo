package com.suimo.loi.myfamily.http;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DownLoadUtils {
    private static final String TAG ="DownLoadUtils";
    public static void downLoad(){
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiMySerer.Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        build.create(ApiMySerer.class)
                .downloadimage()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: 开始下载");
                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        try {
                            //文件大小
                            long contentLength = body.contentLength();
                            //读取文件
                            InputStream inputStream = body.byteStream();

                            //创建一个文件夹
                            File directory = Environment.getExternalStorageDirectory();
//                            File file = new File(directory, "0814-1.apk");
                            File file = new File(directory, "test.gif");
                            FileOutputStream outputStream = new FileOutputStream(file);

                            byte[] bytes = new byte[1024];
                            int len=0;
//                            DownloadInfo downloadInfo = new DownloadInfo();
                            //循环读取文件的内容，把他放到新的文件目录里面
                            while ((len=inputStream.read(bytes))!= -1){
                                outputStream.write(bytes,0,len);
                                long length = file.length();
                                //获取下载的大小，并把它传给页面
                                int progress= (int) (length*100/contentLength);
//                                downloadInfo.progress=progress;
                                Log.i(TAG, "onNext: =====>>"+progress);
//                                EventBus.getDefault().post(downloadInfo);
                            }
                            //当下载完成后，利用粘性发送，并安装
//                            EventBus.getDefault().postSticky(file);
                            Log.i(TAG, "downLoadSuccess");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: e=="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 下载完成");
                    }
                });


    }
}
