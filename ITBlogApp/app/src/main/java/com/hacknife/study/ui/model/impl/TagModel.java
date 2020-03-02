package com.hacknife.study.ui.model.impl;

import com.hacknife.study.api.Api;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.entity.Article;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.http.HttpClient;
import com.hacknife.study.ui.base.impl.BaseModel;
import com.hacknife.study.ui.model.ITagModel;
import com.hacknife.study.ui.viewmodel.ITagViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TagModel extends BaseModel<ITagViewModel> implements ITagModel {
    public TagModel(ITagViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh(String tag) {
        HttpClient.create(Api.class)
                .tagPage(tag, 1, AppConfig.TAG_PAGE_SIZE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.refresh(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.refreshFail( );
                    }
                });
    }

    @Override
    public void loadMore(String tag, int page) {
        HttpClient.create(Api.class)
                .tagPage(tag, page, AppConfig.TAG_PAGE_SIZE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.loadMore(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.loadMoreFail( );
                    }
                });
    }
}
