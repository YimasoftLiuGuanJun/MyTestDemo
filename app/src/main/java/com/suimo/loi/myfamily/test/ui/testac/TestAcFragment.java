package com.suimo.loi.myfamily.test.ui.testac;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suimo.loi.myfamily.R;


public class TestAcFragment extends Fragment {

    private TestAcViewModel mViewModel;

    public static TestAcFragment newInstance() {
        return new TestAcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_ac_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TestAcViewModel.class);
        // TODO: Use the ViewModel
    }

}
