package com.suimo.loi.myfamily.push;

import android.content.Context;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

/**
 * Author：loi on 2019-03-14 18:42
 * Emil：894900183@qq.com
 * - -
 */
public class HuaweiPushReceiver extends PushReceiver {

    @Override
    public void onToken(Context context, String s) {
        super.onToken(context, s);
        Log.e("HuaWeiOnToken","onToken:"+s);
    }
}
