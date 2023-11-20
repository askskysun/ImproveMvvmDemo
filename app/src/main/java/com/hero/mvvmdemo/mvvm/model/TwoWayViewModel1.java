package com.hero.mvvmdemo.mvvm.model;

import android.content.Context;
import android.widget.Toast;

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
    private Context context;
    private LoginModel loginModel;

    public TwoWayViewModel1(Context context) {
        this.context = context;
        loginModel = new LoginModel("测试双向绑定");
    }

    @Bindable
    public String getUserName() {
        return loginModel.getUsername();
    }

    public void setUserName(String username) {
        if (username != null && !username.equals(loginModel.getUsername())) {
            loginModel.setUsername(username);
            Toast.makeText(context, username, Toast.LENGTH_SHORT).show();
            notifyPropertyChanged(BR.userName);
        }
    }
}