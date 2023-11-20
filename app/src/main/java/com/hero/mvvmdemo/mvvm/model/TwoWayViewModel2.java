package com.hero.mvvmdemo.mvvm.model;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hero.mvvmdemo.mvvm.bean.LoginModel;

/**
 * <pre>
 * 测试双向绑定 推荐
 * </pre>
 * Author by sunhaihong, Email 1910713921@qq.com, Date on 2023/11/20.
 */
public class TwoWayViewModel2 extends ViewModel {
    private ObservableField<LoginModel> studentObservableField;

    public TwoWayViewModel2() {
        LoginModel loginModel = new LoginModel("双向绑定推荐");
        studentObservableField = new ObservableField<>();
        studentObservableField.set(loginModel);
    }

    public String getUsername() {
        return studentObservableField.get().getUsername();
    }

    public void setUsername(String name) {
        studentObservableField.get().setUsername(name);
    }
}