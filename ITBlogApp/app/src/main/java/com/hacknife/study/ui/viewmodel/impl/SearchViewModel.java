package com.hacknife.study.ui.viewmodel.impl;

import com.hacknife.study.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.databinding.ActivitySearchBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseViewModel;
import com.hacknife.study.ui.model.ISearchModel;
import com.hacknife.study.ui.model.impl.SearchModel;
import com.hacknife.study.ui.view.ISearchView;
import com.hacknife.study.ui.viewmodel.ISearchViewModel;

import java.util.List;

public class SearchViewModel extends BaseViewModel<ISearchView, ISearchModel, ActivitySearchBinding> implements ISearchViewModel {
    int page = 2;

    public SearchViewModel(ISearchView view, ActivitySearchBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivitySearchBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected ISearchModel createModel() {
        return new SearchModel(this);
    }


    @Override
    public void refresh(String kw) {
        page = 2;
        model.refresh(kw);
        binding.refresh.setNoMoreData(false);
    }

    @Override
    public void loadMore(String kw) {
        model.loadMore(kw, page);
        page++;
    }

    @Override
    public void refresh(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.bindData(articles);
        binding.refresh.finishRefresh();
        if (articles.size() < AppConfig.SEARCH_PAGE_SIZE)
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
