package com.hacknife.study.ui.viewmodel.impl;

import com.hacknife.study.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.databinding.ActivityChannelBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseViewModel;
import com.hacknife.study.ui.model.IChannelModel;
import com.hacknife.study.ui.model.impl.ChannelModel;
import com.hacknife.study.ui.view.IChannelView;
import com.hacknife.study.ui.viewmodel.IChannelViewModel;

import java.util.List;

public class ChannelViewModel extends BaseViewModel<IChannelView, IChannelModel, ActivityChannelBinding> implements IChannelViewModel {
    int page = 2;

    public ChannelViewModel(IChannelView view, ActivityChannelBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityChannelBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IChannelModel createModel() {
        return new ChannelModel(this);
    }


    @Override
    public void refresh(long id) {
        page = 2;
        model.refresh(id);
        binding.refresh.setNoMoreData(false);
    }

    @Override
    public void loadMore(long id) {
        model.loadMore(id, page);
        page++;
    }

    @Override
    public void refresh(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.bindData(articles);
        binding.refresh.finishRefresh();
        if (articles.size() < AppConfig.CHANNEL_PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void refreshFail() {
        binding.refresh.finishRefresh();
        page--;
    }

    @Override
    public void loadMore(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.insert(articles);
        binding.refresh.finishLoadMore();
        if (articles.size() < AppConfig.CHANNEL_PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void loadMoreFail() {
        binding.refresh.finishLoadMore();
        page--;
    }
}
