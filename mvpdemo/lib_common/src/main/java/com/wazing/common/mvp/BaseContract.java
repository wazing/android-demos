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

        /** onDestroy()执行后执行 */
        void onClear();
    }

    interface View {

        /** 显示dialog */
        void showProgressDialog();

        /** 关闭dialog */
        void dismissProgressDialog();

        /** 失败 */
        void onFail(String msg);
    }

    interface Presenter extends LifecycleObserver {

        /** 关联onDestroy()生命周期 */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onClear();
    }

}
