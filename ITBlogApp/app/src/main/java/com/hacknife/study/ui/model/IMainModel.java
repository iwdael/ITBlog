package com.hacknife.study.ui.model;


import com.hacknife.study.ui.base.IBaseModel;

public interface IMainModel extends IBaseModel {

    void refresh();

    void loadMore(int page);
}
