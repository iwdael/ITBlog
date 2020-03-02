package com.hacknife.study.ui.base.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hacknife.slidingactivity.SwipeBackLayout;
import com.hacknife.study.R;
import com.hacknife.study.bus.RxBus;
import com.hacknife.study.bus.ThemeEvent;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.ui.MainActivity;
import com.hacknife.study.ui.base.IBaseView;
import com.hacknife.study.ui.base.IBaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : DataBinding
 * version: 1.0
 */
public abstract class BaseActivity<ViewModel extends IBaseViewModel, DataBinding> extends AppCompatActivity implements IBaseView {
    protected ViewModel viewModel;
    protected DataBinding dataBinding;
    protected CompositeDisposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(BaseActivity.this instanceof MainActivity)) {
            SwipeBackLayout layout = new SwipeBackLayout(this);
            layout.setShadowDrawable(R.drawable.slidingactivity_shadow_left);
            layout.attachToActivity(this);
        }
        dataBinding = performBinding();
        viewModel = performViewModel();
        viewModel.initial();
        disposable = new CompositeDisposable();
        RxBus.toObservable(ThemeEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ThemeEvent>(disposable) {
                    @Override
                    public void onNext(ThemeEvent event) {
                        recreate();
                    }
                });
        init();
    }

    protected void init() {
    }

    protected abstract ViewModel performViewModel();

    protected abstract DataBinding performBinding();

    @Override
    public Application application() {
        return getApplication();
    }

    @Override
    public Context applicationContext() {
        return getApplicationContext();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        viewModel.destroy();
        viewModel = null;
        dataBinding = null;
        disposable.dispose();
        disposable = null;
        super.onDestroy();
    }

    public void startActivity(Class<? extends Activity> clazz, Object... args) {
        Intent intent = new Intent(this, clazz);
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("args must 2 * n !");
        }
        int length = args.length;
        for (int i = 0; i < length; i = i + 2) {
            Object val = args[i + 1];
            if (val instanceof Long) {
                intent.putExtra((String) args[i], (Long) val);
            } else if (val instanceof String) {
                intent.putExtra((String) args[i], (String) val);
            } else if (val instanceof Integer) {
                intent.putExtra((String) args[i], (Integer) val);
            }
        }
        startActivity(intent);
    }
}