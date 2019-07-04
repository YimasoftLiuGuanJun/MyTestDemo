package com.suimo.loi.myfamily.mvp.presenter;

import android.content.Context;

import com.suimo.loi.myfamily.mvp.view.ILicenseView;


/**
 * Created by bob on 17-5-14.
 */

public class LicenseFragmentPresenter extends BasePresenter<ILicenseView> {

        public LicenseFragmentPresenter(Context context, ILicenseView iView) {
                super(context, iView);
        }

        @Override
        public void release() {

        }
}
