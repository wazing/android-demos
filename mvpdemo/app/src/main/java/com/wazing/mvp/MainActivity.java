package com.wazing.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wazing.common.mvp.BaseMvpActivity;
import com.wazing.mvp.contract.MainContract;
import com.wazing.mvp.presenter.MainPresenter;

/**
 * @author wangzheng
 * create time 2019/9/5
 */
public class MainActivity extends BaseMvpActivity<MainContract.Presenter>
        implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getUserInfo("wazing");
            }
        });
    }

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void resultUserInfo(String result) {
        this.<TextView>findViewById(R.id.content).setText(result);
    }

    @Override
    public void onFail(String msg) {

    }


}
