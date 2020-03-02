package com.hacknife.study.ui.viewmodel;


import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.IBaseViewModel;


public interface IArticleViewModel extends IBaseViewModel {

    void loadArticle(long longExtra);

    void loadArticle(Article article);

    void loadFail();

}
