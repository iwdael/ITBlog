package com.hacknife.study.ui;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hacknife.study.R;
import com.hacknife.study.adapter.ArticleAdapter;
import com.hacknife.study.adapter.base.OnItemClickListener;
import com.hacknife.study.constant.ARGS;
import com.hacknife.study.databinding.ActivityChannelBinding;
import com.hacknife.study.entity.Article;
import com.hacknife.study.ui.base.impl.BaseActivity;
import com.hacknife.study.ui.view.IChannelView;
import com.hacknife.study.ui.viewmodel.IChannelViewModel;
import com.hacknife.study.ui.viewmodel.impl.ChannelViewModel;

public class ChannelActivity extends BaseActivity<IChannelViewModel, ActivityChannelBinding> implements IChannelView {

    @Override
    protected IChannelViewModel performViewModel() {
        return new ChannelViewModel(this, dataBinding);
    }

    @Override
    protected ActivityChannelBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_channel);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        dataBinding.toolbarTitle.setText(getIntent().getStringExtra(ARGS.TAG));
        ArticleAdapter adapter = new ArticleAdapter();
        dataBinding.rcView.setAdapter(adapter);
        dataBinding.rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dataBinding.refresh.setOnRefreshListener(refreshLayout -> viewModel.refresh(getIntent().getLongExtra(ARGS.ID,0)));
        dataBinding.refresh.setOnLoadMoreListener(refreshLayout -> viewModel.loadMore(getIntent().getLongExtra(ARGS.ID,0)));
        dataBinding.refresh.autoRefresh();
        adapter.setOnRecyclerViewListener((OnItemClickListener<Article>) article -> startActivity(ArticleActivity.class, ARGS.TITLE, article.getTitle(), ARGS.ID, article.getId()));

    }
}
