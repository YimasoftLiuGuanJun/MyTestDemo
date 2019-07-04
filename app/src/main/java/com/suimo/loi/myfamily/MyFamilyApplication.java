package com.suimo.loi.myfamily;

import android.app.Application;
import android.content.Context;
import android.content.pm.ShortcutManager;
import android.os.Build;
import android.util.Log;


import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.upspushsdklib.UpsPushManager;
import org.litepal.LitePal;

/**
 * Author：loi on 2019-01-21 10:33
 * Emil：894900183@qq.com
 * - -
 */
public class MyFamilyApplication  extends Application {
    public static String UPS_APP_ID = "1003699";
    public static String UPS_APP_KEY = "dcf4e62838ce4e0b9342322519059c8d";


    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        UpsPushManager.register(this,UPS_APP_ID,UPS_APP_KEY);
        Log.e("getPushId",PushManager.getPushId(this));
    }
}
