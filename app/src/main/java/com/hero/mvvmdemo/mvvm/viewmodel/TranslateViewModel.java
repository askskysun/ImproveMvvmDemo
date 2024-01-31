package com.hero.mvvmdemo.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hero.mvvmdemo.mvvm.bean.TranslateBean;
import com.hero.mvvmdemo.mvvm.interfaces.OnTranslateCallBack;
import com.hero.mvvmdemo.mvvm.model.TranslateModel;

public class TranslateViewModel extends ViewModel {

    private MutableLiveData<TranslateBean> mTranslateData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoadingLiveData = new MutableLiveData<>();
    private TranslateModel mTranslateModel = new TranslateModel();

    public TranslateViewModel() {
        //初始化数据
        initData();
    }

    private void initData() {
        TranslateBean translateBean = new TranslateBean();
        translateBean.setTitle("可编辑的标题");
        translateBean.setClickName("点击的名字");
        mTranslateData.setValue(translateBean);
        mLoadingLiveData.setValue(false);
    }

    /**
     * 获取翻译数据
     */
    public void requestTranslateData() {
        //进度条状态
        mLoadingLiveData.setValue(true);
        mTranslateModel.getTranslateData(new OnTranslateCallBack() {
            @Override
            public void onResponse(String response) {
                //进度条状态
                mLoadingLiveData.postValue(false);
                TranslateBean value = mTranslateData.getValue();
                value.setData(response);
                //接口报文
                mTranslateData.postValue(value);
            }

            @Override
            public void onFailure(Throwable t) {
                //进度条状态
                mLoadingLiveData.postValue(false);
                TranslateBean value = mTranslateData.getValue();
                value.setData(t.getMessage());
                //接口报文
                mTranslateData.postValue(value);
            }
        });
    }

    public MutableLiveData<TranslateBean> getmTranslateData() {
        return mTranslateData;
    }

    /**
     * 获取加载中的LiveData对象
     */
    public MutableLiveData<Boolean> getLoadingLiveData() {
        return mLoadingLiveData;
    }
}