package com.hacknife.study.ui.viewmodel;


import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.IBaseViewModel;

import java.util.List;


public interface ISearchViewModel extends IBaseViewModel {

    void refresh(String kw);

    void loadMore(String kw);

    void refresh(List<Article> articles);

    void refreshFail();

    void loadMore(List<Article> articles);

    void loadMoreFail();

}
