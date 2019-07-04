package com.suimo.loi.myfamily.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.gank_client.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by bob on 17-5-2.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
        Unbinder unbinder;
        protected T presenter;

        @Override
        public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
                View view = inflater.inflate(provideViewLayout(), container, false);
                unbinder = ButterKnife.bind(this, view);
                initPresenter();
                return view;
        }

        protected abstract int provideViewLayout();

        protected abstract void initPresenter();

        @Override
        public void onDestroyView() {
                super.onDestroyView();
                unbinder.unbind();
        }

        @Override
        public void onDestroy() {
                super.onDestroy();
                //TODO 检查内存泄露
        }
}
