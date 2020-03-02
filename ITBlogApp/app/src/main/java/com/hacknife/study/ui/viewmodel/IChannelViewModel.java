package com.hacknife.study.ui.viewmodel;


import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.IBaseViewModel;

import java.util.List;


public interface IChannelViewModel extends IBaseViewModel {

    void refresh(long id);

    void loadMore(long id);

    void refresh(List<Article> articles);

    void refreshFail();

    void loadMore(List<Article> articles);

    void loadMoreFail();
}
