package com.hacknife.study.ui.viewmodel.impl;

import com.hacknife.study.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.databinding.ActivityTagBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseViewModel;
import com.hacknife.study.ui.model.ITagModel;
import com.hacknife.study.ui.model.impl.TagModel;
import com.hacknife.study.ui.view.ITagView;
import com.hacknife.study.ui.viewmodel.ITagViewModel;

import java.util.List;

public class TagViewModel extends BaseViewModel<ITagView, ITagModel, ActivityTagBinding> implements ITagViewModel {
    int page = 2;

    public TagViewModel(ITagView view, ActivityTagBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityTagBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected ITagModel createModel() {
        return new TagModel(this);
    }


    @Override
    public void refresh(String tag) {
        page = 2;
        model.refresh(tag);
        binding.refresh.setNoMoreData(false);
    }

    @Override
    public void loadMore(String tag) {
        model.loadMore(tag, page);
        page++;
    }

    @Override
    public void refresh(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.bindData(articles);
        binding.refresh.finishRefresh();
        if (articles.size() < AppConfig.TAG_PAGE_SIZE)
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
        if (articles.size() < AppConfig.TAG_PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void loadMoreFail() {
        binding.refresh.finishLoadMore();
        page--;
    }
}
