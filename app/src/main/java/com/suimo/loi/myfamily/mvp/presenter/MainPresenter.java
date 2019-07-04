package com.suimo.loi.myfamily.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.suimo.loi.myfamily.mvp.view.IBaseView;


/**
 * Created by bob on 17-5-1.
 */

public class MainPresenter extends BasePresenter<IBaseView> {

        public MainPresenter(Context context, IBaseView iView) {
                super(context, iView);
        }

        @Override
        public void release() {

        }

//        public void toDailyActivity() {
//                Intent intent = new Intent(context, DailyActivity.class);
//                context.startActivity(intent);
//        }
//
//        public void toAboutActivity() {
//                Intent intent = new Intent(context, AboutActivity.class);
//                context.startActivity(intent);
//        }
//
//        public void toSettingActivity() {
//                Intent intent = new Intent(context, SettingActivity.class);
//                context.startActivity(intent);
//        }


}
