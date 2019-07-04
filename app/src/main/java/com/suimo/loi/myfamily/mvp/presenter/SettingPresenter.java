package com.suimo.loi.myfamily.mvp.presenter;

import android.content.Context;

import com.suimo.loi.myfamily.mvp.view.IBaseView;


/**
 * Created by bob on 17-5-1.
 */

public class SettingPresenter extends BasePresenter<IBaseView> {
        public SettingPresenter(Context context, IBaseView iView) {
                super(context, iView);
        }

        @Override
        public void release() {

        }
}
