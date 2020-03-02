package com.hacknife.study.ui.model.impl;

import com.hacknife.study.api.Api;
import com.hacknife.study.entity.Article;
import com.hacknife.study.http.Consumer;
import com.hacknife.study.http.HttpClient;
import com.hacknife.study.ui.base.impl.BaseModel;
import com.hacknife.study.ui.model.IChannelModel;
import com.hacknife.study.ui.viewmodel.IChannelViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChannelModel extends BaseModel<IChannelViewModel> implements IChannelModel {
    public ChannelModel(IChannelViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void refresh(long id) {
        HttpClient.create(Api.class)
                .channelPage(id, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.refresh(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.refreshFail();
                    }
                })
        ;
    }

    @Override
    public void loadMore(long id, int page) {
        HttpClient.create(Api.class)
                .channelPage(id, page, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Article>>(disposable) {
                    @Override
                    public void onNext(List<Article> articles) {
                        viewModel.loadMore(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.loadMoreFail();
                    }
                })
        ;
    }
}
