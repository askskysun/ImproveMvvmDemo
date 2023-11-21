package com.hero.mvvmdemo.mvvm.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hero.mvvmdemo.R;
import com.hero.mvvmdemo.databinding.ActivityMvvmpatternBinding;
import com.hero.mvvmdemo.mvvm.interfaces.Presenter;
import com.hero.mvvmdemo.mvvm.model.TwoWayViewModel1;
import com.hero.mvvmdemo.mvvm.model.TwoWayViewModel2;
import com.hero.mvvmdemo.mvvm.model.FanYiViewModel;

/**
 * View层
 */
public class MVVMActivity extends AppCompatActivity {

    private FanYiViewModel mFanYiViewModel;
    private TwoWayViewModel2 mTwoWayViewModel2;
    private ActivityMvvmpatternBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvmpattern);
        //双向绑定的示例
        twoWayViewModel();
        //点击事件和网络请求的示例
        clickAndRequestViewModel();
        //自定义BindingAdapter
        customsBindingAdapter();
    }

    /**
     * 自定义BindingAdapter 的示例
     */
    private void customsBindingAdapter() {
        binding.setImageUrl("https://img.win3000.com/m00/d7/1a/d685398ab14eb827da7a883931cf38af.jpg");
        binding.setDefaultImageResource(R.drawable.whiteboard_blue);
    }

    /**
     * 双向绑定的示例
     */
    private void twoWayViewModel() {
        //测试双向绑定 2种方式
        binding.setTwoWayViewModel1(new TwoWayViewModel1(this));
        //获取ViewModelProvider实例
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        //获取ViewModel实例
        mTwoWayViewModel2 = viewModelProvider.get(TwoWayViewModel2.class);
        mTwoWayViewModel2.getLoginModelObserLiveData().observe(this, loginModel -> {
            Toast.makeText(this, loginModel.getUsername(), Toast.LENGTH_SHORT).show();
        });
        binding.setTwoWayViewModel2(mTwoWayViewModel2);
    }

    /**
     * 点击事件和网络请求的示例
     */
    private void clickAndRequestViewModel() {
        //点击事件的处理
        binding.setPresenter(new Presenter() {
            @Override
            public void onClick(View v) {
                if (v == binding.activityViewmodelTv) {
                    mFanYiViewModel.getFanYiData();
                }
            }
        });

        binding.setFanyiData("点击获取翻译");

        //获取ViewModelProvider实例
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        //获取ViewModel实例
        mFanYiViewModel = viewModelProvider.get(FanYiViewModel.class);
        //观察接口请求
        mFanYiViewModel.getListLiveData().observe(this, str -> {
            binding.setFanyiData(str);
            Log.d("ViewModelActivity", "接口请求s----:" + str);
            Log.d("ViewModelActivity", "接口请求线程----:" + Thread.currentThread().getName());
        });
        //观察接口请求中...
        mFanYiViewModel.getLoadingLiveData().observe(this, aBoolean -> {
            binding.activityViewmodelPb.setVisibility(aBoolean ? View.GONE : View.VISIBLE);
            Log.d("ViewModelActivity", "观察接口请求aBoolean----:" + aBoolean);
            Log.d("ViewModelActivity", "观察接口请求线程----:" + Thread.currentThread().getName());
        });
    }
}
