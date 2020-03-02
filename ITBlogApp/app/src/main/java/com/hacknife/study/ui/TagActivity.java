package com.hacknife.study.ui;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hacknife.study.R;
import com.hacknife.study.adapter.ArticleAdapter;
import com.hacknife.study.adapter.base.OnItemClickListener;
import com.hacknife.study.constant.ARGS;
import com.hacknife.study.databinding.ActivityTagBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseActivity;
import com.hacknife.study.ui.view.ITagView;
import com.hacknife.study.ui.viewmodel.ITagViewModel;
import com.hacknife.study.ui.viewmodel.impl.TagViewModel;

public class TagActivity extends BaseActivity<ITagViewModel, ActivityTagBinding> implements ITagView {

    @Override
    protected ITagViewModel performViewModel() {
        return new TagViewModel(this, dataBinding);
    }

    @Override
    protected ActivityTagBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_tag);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        dataBinding.toolbarTitle.setText(getIntent().getStringExtra(ARGS.TAG));
        ArticleAdapter adapter = new ArticleAdapter();
        dataBinding.rcView.setAdapter(adapter);
        dataBinding.rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh(getIntent().getStringExtra(ARGS.TAG)));
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore(getIntent().getStringExtra(ARGS.TAG)));
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener<Article>) article -> startActivity(ArticleActivity.class, ARGS.TITLE, article.getTitle(), ARGS.ID, article.getId()));

    }
}
