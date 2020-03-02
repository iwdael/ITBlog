package com.hacknife.study.ui.model.impl;

import com.hacknife.study.api.Api;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.entity.Article;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.http.HttpClient;
import com.hacknife.study.ui.base.impl.BaseModel;
import com.hacknife.study.ui.model.ISearchModel;
import com.hacknife.study.ui.viewmodel.ISearchViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel extends BaseModel<ISearchViewModel> implements ISearchModel {
    public SearchModel(ISearchViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh(String kw) {
        HttpClient.create(Api.class)
                .search(kw, 1, AppConfig.SEARCH_PAGE_SIZE)
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
    public void loadMore(String kw, int page) {
        HttpClient.create(Api.class)
                .search(kw, page, AppConfig.SEARCH_PAGE_SIZE)
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
