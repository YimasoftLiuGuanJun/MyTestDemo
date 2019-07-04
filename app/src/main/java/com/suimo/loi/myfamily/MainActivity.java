package com.suimo.loi.myfamily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.suimo.loi.myfamily.ui.activity.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText) EditText aaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        aaa.setText("123456");
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);//不销毁程序而只是回退到桌面
//        super.onBackPressed();
    }
}
