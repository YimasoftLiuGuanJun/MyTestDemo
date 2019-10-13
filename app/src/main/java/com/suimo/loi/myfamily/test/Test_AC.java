package com.suimo.loi.myfamily.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.suimo.loi.myfamily.DataBaseBean.TestClass;
import com.suimo.loi.myfamily.R;
import com.suimo.loi.myfamily.base.BaseActivity;
import com.suimo.loi.myfamily.base.ToolBarActivity;
import com.suimo.loi.myfamily.http.DownLoadUtils;
import com.suimo.loi.myfamily.login.Model.UserBean;
import com.suimo.loi.myfamily.login.Presenter.LoginPresenter;
import com.suimo.loi.myfamily.mvp.model.entity.Meizi;
import com.suimo.loi.myfamily.mvp.presenter.DailyPresenter;
import com.suimo.loi.myfamily.mvp.view.IDailyView;
import com.suimo.loi.myfamily.test.RxjavaAndRetrofit.IService;
import com.suimo.loi.myfamily.test.RxjavaAndRetrofit.MyLoginPostBodyBean;
import com.suimo.loi.myfamily.test.RxjavaAndRetrofit.MyLoginService;
import com.suimo.loi.myfamily.test.RxjavaAndRetrofit.WeatherBean;
import com.suimo.loi.myfamily.test.ui.testac.TestAcFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Thread.sleep;

public class Test_AC extends BaseActivity<DailyPresenter> implements IDailyView {

    @BindView(R.id.imageView)
    ImageView imageView;
    private static final String TAG = "Test_AC";
//    private final String BASE_URL = "http://47.101.136.5:3005/Service.asmx/ExecuteSql";
    private final String BASE_URL = "http://47.101.136.5:3005/Service.asmx/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test__ac_activity);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, TestAcFragment.newInstance())
//                    .commitNow();
//        }

        testFunction();
        initPresenter();

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.test__ac_activity;
    }

    @Override
    protected void initPresenter() {
        presenter = new DailyPresenter(this,this );
        presenter.init();

        /**加载本地图片
        File file = new File(getExternalCacheDir() + "/image.jpg");
        Glide.with(this).load(file).into(imageView);

        // 加载应用资源
        int resource = R.drawable.image;
        Glide.with(this).load(resource).into(imageView);

        // 加载二进制流
        byte[] image = getImageBytes();
        Glide.with(this).load(image).into(imageView);

        // 加载Uri对象
        Uri imageUri = getImageUri();
        Glide.with(this).load(imageUri).into(imageView);
        */
    }


    private void testFunction(){
        com.suimo.loi.myfamily.DataBaseBean.UserBean userBean = new com.suimo.loi.myfamily.DataBaseBean.UserBean();
        userBean.setName(System.currentTimeMillis()+"");
        userBean.setPrice(1);
        List<TestClass> testClasses = new ArrayList<TestClass>();
        testClasses.add(new TestClass("hello"));
        userBean.setTestClasses(testClasses);
        userBean.save();

//        Observable.just(this)
//                .map((context)->{login(getUserId(context))})
//                .map((context)->{initSDK(context)})
//                .map((context)->{initDatabase(context)})
//                .subscribeOn(Schedulers.newThread())
//                .subscribe((context)->{startActivity()});

//        Observable obserInitSDK=Observable.create((context)->{initSDK(context)}).subscribeOn(Schedulers.newThread());
//
//        Observable obserInitDB=Observable.create((context)->{initDatabase(context)}).subscribeOn(Schedulers.newThread());
//
//        Observable obserLogin=Observable.create((context)->{login(getUserId(context))})
//                .map((isLogin)->{returnContext()})
//                .subscribeOn(Schedulers.newThread());
//
//        Observable observable = Observable.merge(obserInitSDK,obserInitDB,obserLogin);
//
//        observable.subscribe(()->{startActivity(new Intent(this,MainActivity.class));});

        Observable
                .just("hello", "worlddd")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.e(TAG, s));

        //https://gank.io/post/560e15be2dca930e00da1083
        //1 创建 Observer(观察者)
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "Item: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error!");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "Completed!");
            }
        };
        //1.1 Subscriber 是 Observer 的抽象类
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.e(TAG, "-------onSubscribe " );
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "-------Item: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "-------Error!");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "-------Completed!");
            }
        };

        //2创建 Observable（被观察者）
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            // create() 是 RxJava 最基本的创造事件序列的方法
            // 此处传入了一个 OnSubscribe 对象参数
            // 当 Observable 被订阅时，OnSubscribe 的 call() 方法会自动被调用，即事件序列就会依照设定依次被触发
            // 即观察者会依次调用对应事件的复写方法从而响应事件
            // 从而实现被观察者调用了观察者的回调方法 & 由被观察者向观察者的事件传递，即观察者模式

            // 2. 在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // 通过 ObservableEmitter类对象产生事件并通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                emitter.onNext("Hello");
                emitter.onNext("Hi");
                emitter.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

        observable.subscribe(observer);


        /////////////////////////////////////////////////////////
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e(TAG,"onNext:"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete()");
                    }
                });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                sleep(3000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, integer + "----");
                    }
                }, new io.reactivex.functions.Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });


        int[] drawableRes = new int[]{
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp,
                R.drawable.ic_favorite_red_300_18dp};
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> emitter) throws Exception {
                for (int i=0;i<drawableRes.length;i++){
                    Drawable drawable=getResources().getDrawable(drawableRes[i]);
                    //第6个图片延时3秒后架子
                    if (i==5){
                        sleep(3000);
                    }
                    //复制第7张图片到sd卡
                    if (i==6){
                        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
//                        saveBitmap(bitmap,"test.png", Bitmap.CompressFormat.PNG);
                        Log.e(TAG,"模拟 复制第7张图片到sd卡");
                    }
                    //上传到网络
                    if (i==7){
//                        updateIcon(drawable);
                        Log.e(TAG,"模拟 上传到网络");
                    }
                    emitter.onNext(drawable);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drawable>() {
                    @Override
                    public void accept(Drawable drawable) throws Exception {
                        //回调后在UI界面上展示出来
                        Log.e(TAG,"回调后在UI界面上展示出来");
                    }
                });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//必须压迫配置！！！
                .baseUrl(BASE_URL)
                .build();

        MyLoginService myLoginService = retrofit.create(MyLoginService.class);

        myLoginService.login1("select * from UserInfo where userId = 9527") //获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())//请求完成后在io线程中执行
                .doOnNext(new Consumer<Response<List<UserBean>>>() {
                    @Override
                    public void accept(Response<List<UserBean>> userBeans) throws Exception {
                        Log.e(TAG,"测试1："+userBeans);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<Response<List<UserBean>>>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        Log.e(TAG,"测试2："+disposable);
                    }

                    @Override
                    public void onNext(Response<List<UserBean>> userBeans) {
                        Log.e(TAG,"测试3："+userBeans);
                        Glide.with(getApplicationContext())
//                      .load("https://www.baidu.com/img/bd_logo1.png")
//                      .load("http://47.101.136.5:3006/501152.jpg")//加载的URL
                                .load("http://47.101.136.5:3006/gif1.gif")//加载的URL
                                .crossFade()//动画--渐隐渐现动画
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁止缓存
                                .placeholder(R.drawable.ic_cloud_download_black_24dp)//占位图
                                .error(R.drawable.ic_error_red_500_24dp)//加载错误图
                                .into(imageView);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG,"测试4："+throwable);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"测试5：onComplete");
                    }
                });

//        Call<List<UserBean>> call = myLoginService.login("select * from UserInfo");
//
//        call.enqueue(new Callback<List<UserBean>>() {
//            @Override
//            public void onResponse(Call<List<UserBean>> call, Response<List<UserBean>> response) {
//                Log.e(TAG,"成功！！！！！");
//            }
//
//            @Override
//            public void onFailure(Call<List<UserBean>> call, Throwable t) {
//                Log.e(TAG,"失败！！！！！");
//            }
//        });

        toRequestWeatherInfo();

        DownLoadUtils.downLoad();

        ShortcutManager shortcutManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            shortcutManager = getSystemService(ShortcutManager.class);
            ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "shortcutId3")
                    .setShortLabel("Web site")
                    .setLongLabel("Open the web site")
                    .setIcon(Icon.createWithResource(this, R.drawable.hms_cancel))
                    .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://wuxiaolong.me/")))
                    .build();
            try {
                shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** 去请求天气信息*/
    private void toRequestWeatherInfo() {
        Retrofit retrofit = new Retrofit.Builder()
                //RequestHttp.WeatherBaseUrl = "http://www.weather.com.cn/"
                .baseUrl("http://www.weather.com.cn/")
                //记得添加相关依赖
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IService weather = retrofit.create(IService.class);
        Call<WeatherBean> weatherBeanCall = weather.weatherInfo("101010100.html");
        //这里只是写了异步请求，你也可以进行同步请求
        weatherBeanCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                Log.i("Tag123",response.body().getWeatherinfo().getCity()+"-"+response.body().getWeatherinfo().getWD());
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                Log.i("Tag123","onFailure");
            }
        });
    }

    @Override
    public void showProgress() {
        Log.e(TAG,"showProgress");
    }

    @Override
    public void hideProgress() {
        Log.e(TAG,"hideProgress");
    }

    @Override
    public void showErrorView() {
        Log.e(TAG,"showErrorView");
    }

    @Override
    public void showNoMoreData() {
        Log.e(TAG,"showNoMoreData");
    }

    @Override
    public void showMeiZiList(List<Meizi> meiziList) {
        Log.e(TAG,"showMeiZiList:"+meiziList);
    }

    @Override
    public void init() {
        presenter.fetchMeiZiData(1);
    }
    public void download(File file){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){

            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri=Uri.fromFile(file);
            intent.setDataAndType(uri,"application/vnd.android.package-archive");
            this.startActivityForResult(intent,2);
        }else {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uriFile= FileProvider.getUriForFile(this,"",file);
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uriFile,"application/vnd.android.package-archive");
            this.startActivityForResult(intent,2);
        }
    }















    interface Interface{
        void show();
    }

    static abstract class Base implements Interface{
        abstract int baseFunction();

        public void print(){
            System.out.print("Base---"+baseFunction()+"\n");
        }

        public void show() {
            System.out.print("Base---show\n");
        }

        private void T1(){}
        void T3(){}
        protected void T2(){}
        public void T4(){}
    }

    public static class A extends Base{
        public void print(){
            super.print();
            System.out.print("A---\n");
            show();
        }

        @Override
        protected void T2() {
            super.T2();
        }

        @Override
        void T3() {
            super.T3();
        }

        @Override
        public void T4() {
            super.T4();
        }

        @Override
        int baseFunction() {
            return 0;
        }

        @Override
        public void show() {
            super.show();
            System.out.print("A---show\n");
        }
    }
    static class B extends A{

        public B(){}

        @Override//不写OverRide也行
        public void print() {
            super.print();
            System.out.print("B---\n");
        }
    }

    protected static class C extends A{

        public C(){}

        @Override//不写OverRide也行
        public void print() {
//            super.print();
            System.out.print("C---\n");
        }
    }

    public static void main(String[] argc){
//        B b = new B();
//        b.print();
        A a = new B();a.print();
        A c = new C();c.print();
    }

}
