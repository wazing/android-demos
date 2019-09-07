package com.wazing.common.mvp;

/**
 * @author wangzheng
 * create time 2019/9/6
 */
public interface ResultCallback<T> {

    void onSuccess(T result);

    void onFail(String msg);

}
