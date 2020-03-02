package com.hacknife.study.ui.model.impl;

import com.hacknife.study.api.Api;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.entity.Article;
import com.hacknife.study.entity.Channel;
import com.hacknife.study.entity.Tag;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.http.HttpClient;
import com.hacknife.study.ui.base.impl.BaseModel;
import com.hacknife.study.ui.model.IMainModel;
import com.hacknife.study.ui.viewmodel.IMainViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel extends BaseModel<IMainViewModel> implements IMainModel {
    public MainModel(IMainViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh() {
        HttpClient.create(Api.class).page(1, AppConfig.MAIN_PAGE_SIZE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.articleRefresh(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.articleRefreshFail();
                    }
                });
    }

    @Override
    public void loadMore(int page) {
        HttpClient.create(Api.class).page(page, AppConfig.MAIN_PAGE_SIZE)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.articleLoad(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.articleLoadFail();
                    }
                });
    }

    @Override
    public void initial() {
        HttpClient.create(Api.class)
                .tags()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Tag>>(disposable) {
                    @Override
                    public void onNext(List<Tag> tags) {
                        viewModel.tags(tags);
                    }
                });
        HttpClient.create(Api.class)
                .channel()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Channel>>(disposable) {
                    @Override
                    public void onNext(List<Channel> tags) {
                        viewModel.channel(tags);
                    }
                });
    }
}
