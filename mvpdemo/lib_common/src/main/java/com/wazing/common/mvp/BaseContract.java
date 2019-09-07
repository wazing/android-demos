package com.wazing.common.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author wangzheng
 * create time 2019/9/5
 */
public interface BaseContract {

    interface Model {

        void onClear();
    }

    interface View {

        void showProgressDialog();

        void dismissProgressDialog();

        /** 失败 */
        void onFail(String msg);
    }

    interface Presenter extends LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onClear();
    }

}
