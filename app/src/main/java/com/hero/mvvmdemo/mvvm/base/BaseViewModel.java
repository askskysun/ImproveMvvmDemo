package com.hero.mvvmdemo.mvvm.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

/**
 * <pre>
 *
 * </pre>
 */
public abstract class BaseViewModel<M extends BaseModel>{

    protected M model;

    public BaseViewModel() {
        model = createModel();
    }

    protected abstract M createModel();

    public void destory(){
        model.destory();
    }
}