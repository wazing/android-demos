package com.wazing.mvp.presenter;

import android.util.Log;

import com.wazing.common.mvp.ResultCallback;
import com.wazing.mvp.contract.MainContract;
import com.wazing.mvp.model.MainModel;

/**
 * @author wangzheng
 * create time 2019/9/5
 */
public class MainPresenter implements MainContract.Presenter {

    private final MainContract.Model mModel;
    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getUserInfo(String username) {
        mView.showProgressDialog();
        mModel.getUserInfo(username, new ResultCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mView.dismissProgressDialog();
                mView.resultUserInfo(result);
            }

            @Override
            public void onFail(String msg) {
                mView.dismissProgressDialog();
                mView.onFail(msg);
            }
        });
    }

    @Override
    public void onClear() {
        mModel.onClear();
    }
}
