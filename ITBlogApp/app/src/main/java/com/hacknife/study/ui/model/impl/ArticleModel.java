package com.hacknife.study.ui.model.impl;

import com.hacknife.study.api.Api;
import com.hacknife.study.entity.Article;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.http.HttpClient;
import com.hacknife.study.ui.base.impl.BaseModel;
import com.hacknife.study.ui.model.IArticleModel;
import com.hacknife.study.ui.viewmodel.IArticleViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleModel extends BaseModel<IArticleViewModel> implements IArticleModel {
    public ArticleModel(IArticleViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void loadArticle(long id) {
        HttpClient.create(Api.class)
                .article(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Article>(disposable){
                    @Override
                    public void onNext(Article article) {
                         viewModel.loadArticle(article);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.loadFail();
                    }
                });
    }
}
