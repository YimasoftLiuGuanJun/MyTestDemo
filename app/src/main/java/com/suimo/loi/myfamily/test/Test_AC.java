package com.suimo.loi.myfamily.test;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.suimo.loi.myfamily.DataBaseBean.TestClass;
import com.suimo.loi.myfamily.R;
import com.suimo.loi.myfamily.login.Presenter.LoginPresenter;
import com.suimo.loi.myfamily.test.ui.testac.TestAcFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Test_AC extends AppCompatActivity {

    private static final String TAG = "Test_AC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test__ac_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestAcFragment.newInstance())
                    .commitNow();
        }
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
                .just("hello", "world")
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
        });

        observable.subscribe(observer);
// 或者：
//        observable.subscribe((Consumer<? super String>) subscriber);



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
}
