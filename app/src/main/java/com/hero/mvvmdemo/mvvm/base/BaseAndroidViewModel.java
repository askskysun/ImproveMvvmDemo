package com.hero.mvvmdemo.mvvm.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

/**
 * <pre>
 * AndroidViewModel的基类
 * </pre>
 */
public abstract class BaseAndroidViewModel<M extends BaseModel> extends AndroidViewModel {

    protected M model;

    public BaseAndroidViewModel(Application application) {
        super(application);
        model = createModel();
    }

    protected abstract M createModel();

    public void destory(){
        model.destory();
    }
}