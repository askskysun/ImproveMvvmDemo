package com.hero.mvvmdemo.mvvm.model;

import androidx.lifecycle.ViewModel;

import com.hero.mvvmdemo.mvvm.ObserMutableLiveData;
import com.hero.mvvmdemo.mvvm.bean.LoginModel;

/**
 * <pre>
 * 测试双向绑定 推荐
 * </pre>
 * Author by sunhaihong, Email 1910713921@qq.com, Date on 2023/11/20.
 */
public class TwoWayViewModel2 extends ViewModel {

    private ObserMutableLiveData<LoginModel> loginModelObserLiveData;

    public TwoWayViewModel2() {
        LoginModel loginModel = new LoginModel("双向绑定推荐：姓名");
        loginModelObserLiveData = new ObserMutableLiveData<>();
        loginModelObserLiveData.set(loginModel);
    }

    public String getUsername() {
        return loginModelObserLiveData.get().getUsername();
    }

    public void setUsername(String name) {
        loginModelObserLiveData.get().setUsername(name);
        loginModelObserLiveData.notifyLiveData();
    }

    public ObserMutableLiveData<LoginModel> getLoginModelObserLiveData() {
        return loginModelObserLiveData;
    }
}