package com.hero.mvvmdemo.mvvm.model;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hero.mvvmdemo.BR;
import com.hero.mvvmdemo.mvvm.bean.LoginModel;

/**
 * <pre>
 * 测试双向绑定 第一种
 * </pre>
 */
public class TwoWayViewModel1 extends BaseObservable {
    public static final String TAG = "TwoWayViewModel1";
    private LoginModel loginModel;

    public TwoWayViewModel1() {
        loginModel = new LoginModel("测试双向绑定");
    }

    @Bindable
    public String getUserName() {
        return loginModel.getUsername();
    }

    public void setUserName(String username) {
        if (username != null && !username.equals(loginModel.getUsername())) {
            loginModel.setUsername(username);
            notifyPropertyChanged(BR.userName);
            Log.i(TAG, "setUserName: "+ username);
        }
    }
}