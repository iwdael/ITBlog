package com.hacknife.study.ui;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hacknife.study.R;
import com.hacknife.study.adapter.ArticleAdapter;
import com.hacknife.study.adapter.base.OnItemClickListener;
import com.hacknife.study.constant.ARGS;
import com.hacknife.study.databinding.ActivitySearchBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseActivity;
import com.hacknife.study.ui.view.ISearchView;
import com.hacknife.study.ui.viewmodel.ISearchViewModel;
import com.hacknife.study.ui.viewmodel.impl.SearchViewModel;

public class SearchActivity extends BaseActivity<ISearchViewModel, ActivitySearchBinding> implements ISearchView {

    @Override
    protected ISearchViewModel performViewModel() {
        return new SearchViewModel(this, dataBinding);
    }

    @Override
    protected ActivitySearchBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_search);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        dataBinding.toolbarTitle.setText(getIntent().getStringExtra(ARGS.SEARCH));
        ArticleAdapter adapter = new ArticleAdapter();
        dataBinding.rcView.setAdapter(adapter);
        dataBinding.rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh(getIntent().getStringExtra(ARGS.SEARCH)));
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore(getIntent().getStringExtra(ARGS.SEARCH)));
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener<Article>) article -> startActivity(ArticleActivity.class, ARGS.TITLE, article.getTitle(), ARGS.ID, article.getId()));

    }
}
