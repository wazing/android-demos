package com.wazing.mvp.model;

import com.wazing.common.mvp.ResultCallback;
import com.wazing.mvp.contract.MainContract;
import com.wazing.mvp.network.RetrofitHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author wangzheng
 * create time 2019/9/5
 */
public class MainModel implements MainContract.Model {

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void getUserInfo(String username, final ResultCallback<String> callback) {
        final Disposable disposable = RetrofitHelper.getInstance().getApiService()
                .getUserInfo(username)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String result) throws Exception {
                        callback.onSuccess(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.onFail(throwable.getMessage());
                    }
                });
        mDisposables.add(disposable);
    }

    @Override
    public void onClear() {
        mDisposables.clear();
    }

}
