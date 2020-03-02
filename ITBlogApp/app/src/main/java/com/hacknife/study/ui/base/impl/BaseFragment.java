package com.hacknife.study.ui.base.impl;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.hacknife.study.ui.base.IBaseView;
import com.hacknife.study.ui.base.IBaseViewModel;

public abstract class BaseFragment<ViewModel extends IBaseViewModel, DataBinding extends ViewDataBinding> extends Fragment implements IBaseView {
    protected ViewModel viewModel;
    protected DataBinding dataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = performBinding(inflater, container);
        viewModel = performViewModel();
        viewModel.initial();
        return dataBinding.getRoot();
    }


    protected abstract ViewModel performViewModel();

    protected abstract DataBinding performBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public Application application() {
        return getActivity().getApplication();
    }

    @Override
    public Context applicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onDestroy() {
        viewModel.destroy();
        viewModel = null;
        dataBinding = null;
        super.onDestroy();
    }
}
