package com.hacknife.study.ui.base.impl;

import android.app.Application;
import android.content.Context;

import com.hacknife.study.ui.base.IBaseModel;
import com.hacknife.study.ui.base.IBaseViewModel;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseModel<VM extends IBaseViewModel> implements IBaseModel {
    protected VM viewModel;
    protected CompositeDisposable disposable;
    public BaseModel(VM viewModel) {
        this.viewModel = viewModel;
        disposable = new CompositeDisposable();
    }

    @Override
    public void initial() {
    }

    @Override
    public Application application() {
        return viewModel.application();
    }

    @Override
    public Context applicationContext() {
        return viewModel.applicationContext();
    }

    @Override
    public Context context() {
        return viewModel.context();
    }

    @Override
    public void destroy() {
        viewModel = null;
        disposable.dispose();
        disposable = null;
    }
}