package com.hacknife.study.ui.viewmodel.impl;

import com.hacknife.study.adapter.TagsAdapter;
import com.hacknife.study.adapter.base.BaseRecyclerViewAdapter;
import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.databinding.ActivityMainBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.entity.Channel;
import com.hacknife.study.entity.Tag;
import com.hacknife.study.ui.base.impl.BaseViewModel;
import com.hacknife.study.ui.model.IMainModel;
import com.hacknife.study.ui.model.impl.MainModel;
import com.hacknife.study.ui.view.IMainView;
import com.hacknife.study.ui.viewmodel.IMainViewModel;

import java.util.List;

public class MainViewModel extends BaseViewModel<IMainView, IMainModel, ActivityMainBinding> implements IMainViewModel {
    int page = 2;

    public MainViewModel(IMainView view, ActivityMainBinding binding) {
        super(view, binding);
    }

    @Override
    protected void binding(ActivityMainBinding binding) {
        binding.setViewModel(this);
    }

    @Override
    protected IMainModel createModel() {
        return new MainModel(this);
    }


    @Override
    public void refresh() {
        page = 2;
        model.refresh();
        binding.refresh.setNoMoreData(false);
    }

    @Override
    public void loadMore() {
        model.loadMore(page);
        page++;
    }

    @Override
    public void articleLoad(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.insert(articles);
        binding.refresh.finishLoadMore();
        if (articles.size() < AppConfig.MAIN_PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void articleRefresh(List<Article> articles) {
        BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) binding.rcView.getAdapter();
        adapter.bindData(articles);
        binding.refresh.finishRefresh();
        if (articles.size() < AppConfig.MAIN_PAGE_SIZE)
            binding.refresh.setNoMoreData(true);
    }

    @Override
    public void tags(List<Tag> tags) {
        TagsAdapter<Tag> adapter = (TagsAdapter<Tag>) binding.flowTag.getAdapter();
        adapter.bindData(tags);
    }

    @Override
    public void channel(List<Channel> tags) {
        TagsAdapter<Channel> adapter = (TagsAdapter<Channel>) binding.flowChannel.getAdapter();
        adapter.bindData(tags);
    }

    @Override
    public void articleRefreshFail() {
        binding.refresh.finishRefresh();
        page--;
    }

    @Override
    public void articleLoadFail() {
        binding.refresh.finishLoadMore();
        page--;
    }
}
