package com.hacknife.study.ui.viewmodel;


import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.IBaseViewModel;

import java.util.List;


public interface ITagViewModel extends IBaseViewModel {

    void refresh(String tag);

    void loadMore(String tag);

    void refresh(List<Article> articles);

    void refreshFail();

    void loadMore(List<Article> articles);

    void loadMoreFail();
}
