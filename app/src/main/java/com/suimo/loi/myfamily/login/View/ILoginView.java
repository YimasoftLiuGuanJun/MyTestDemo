package com.suimo.loi.myfamily.login.View;

import android.app.Activity;
import android.content.Context;

/**
 * Author：${loi} on 2019-04-29 09:43
 * Emil：894900183@qq.com
 * - -
 */

public interface ILoginView {
    Activity getContext();
    void showLoading();
    void onPassWordError();
    void onPassWordCorrect();
    void onError();
}
