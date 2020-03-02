package com.hacknife.study.ui.model;


import com.hacknife.study.ui.base.IBaseModel;

public interface IChannelModel extends IBaseModel {

    void refresh(long id);

    void loadMore(long id, int page);
}
