package com.wazing.mvp.contract;

import com.wazing.common.mvp.BaseContract;
import com.wazing.common.mvp.ResultCallback;

/**
 * @author wangzheng
 * create time 2019/9/6
 */
public interface MainContract {

    interface Model extends BaseContract.Model {

        void getUserInfo(String username, ResultCallback<String> callback);
    }

    interface Presenter extends BaseContract.Presenter {

        void getUserInfo(String username);
    }

    interface View extends BaseContract.View {

        void resultUserInfo(String result);
    }

}
