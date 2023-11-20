package com.hero.mvvmdemo.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hero.mvvmdemo.mvvm.api.FanYiService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FanYiViewModel extends ViewModel {

    /**
     * Retrofit模块 详解：https://blog.csdn.net/weixin_37730482/category_6875815.html
     */
    private Retrofit mRetrofit;
    private FanYiService mFanYiService;
    private String mBaseUrl = "http://fanyi.youdao.com/";

    private MutableLiveData<String> mListLiveData;
    private MutableLiveData<Boolean> mLoadingLiveData;

    public FanYiViewModel() {
        //获取Retrofit对象
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)//设置BaseUrl 必须以'/'结尾
                .build();
        mListLiveData = new MutableLiveData<>();
        mLoadingLiveData = new MutableLiveData<>();
    }

    /**
     * 获取翻译数据
     */
    public void getFanYiData() {
        //进度条状态
        mLoadingLiveData.setValue(false);
        //Retrofit接口请求
        mFanYiService = mRetrofit.create(FanYiService.class);
        Call<ResponseBody> getCall = mFanYiService.getFanYiByGet();
        getCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;//报文 获取报文必须判断响应是否成功
                if (response.isSuccessful()) {
                    try {
                        result = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //进度条状态
                mLoadingLiveData.setValue(true);
                //接口报文
                mListLiveData.setValue(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //进度条状态
                mLoadingLiveData.setValue(true);
                //接口报文
                mListLiveData.setValue(t.getMessage());
            }
        });
    }

    /**
     * 获取请求结果的LiveData对象
     */
    public MutableLiveData<String> getListLiveData() {
        return mListLiveData;
    }

    /**
     * 获取加载中的LiveData对象
     */
    public MutableLiveData<Boolean> getLoadingLiveData() {
        return mLoadingLiveData;
    }
}