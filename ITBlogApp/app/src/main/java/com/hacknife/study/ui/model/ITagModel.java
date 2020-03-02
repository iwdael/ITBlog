package com.hacknife.study.ui.model;


import com.hacknife.study.ui.base.IBaseModel;

public interface ITagModel extends IBaseModel {

    void refresh(String tag);

    void loadMore(String tag, int page);
}
