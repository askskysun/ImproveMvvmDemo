package com.hero.mvvmdemo.mvvm;

import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * <pre>
 * 双向绑定的livedata组合
 * 根据需要再拓展
 * </pre>
 * Author by sunhaihong, Email 1910713921@qq.com, Date on 2023/11/21.
 */
public class ObserMutableLiveData<T> {
    private MutableLiveData<T> mMutableLiveData;
    private ObservableField<T> mObserData;
    private T value;

    public ObserMutableLiveData() {
        mMutableLiveData = new MutableLiveData<>();
        mObserData = new ObservableField<>();
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        mObserData.set(this.value);
        mMutableLiveData.setValue(this.value);
    }

    /**
     * 当数据改变时需要调用下这个
     */
    public void notifyLiveData() {
        //主线程
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mMutableLiveData.setValue(this.value);
            return;
        }
        //子线程
        mMutableLiveData.postValue(this.value);
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        mMutableLiveData.observe(owner, observer);
    }
}
