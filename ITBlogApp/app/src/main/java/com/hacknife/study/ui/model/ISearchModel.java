package com.hacknife.study.ui.model;


import com.hacknife.study.ui.base.IBaseModel;

public interface ISearchModel extends IBaseModel {

    void refresh(String kw);

    void loadMore(String kw, int page);
}
