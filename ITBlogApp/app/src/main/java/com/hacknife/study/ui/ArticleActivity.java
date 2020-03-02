package com.hacknife.study.ui;


import androidx.databinding.DataBindingUtil;

import com.hacknife.study.R;
import com.hacknife.study.constant.ARGS;
import com.hacknife.study.ui.base.impl.BaseActivity;
import com.hacknife.study.ui.view.IArticleView;
import com.hacknife.study.ui.viewmodel.impl.ArticleViewModel;
import com.hacknife.study.ui.viewmodel.IArticleViewModel;
import com.hacknife.study.databinding.ActivityArticleBinding;

public class ArticleActivity extends BaseActivity<IArticleViewModel, ActivityArticleBinding> implements IArticleView {

    @Override
    protected IArticleViewModel performViewModel() {
        return new ArticleViewModel(this, dataBinding);
    }

    @Override
    protected ActivityArticleBinding performBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_article);
    }

    @Override
    protected void init() {
        dataBinding.ivBack.setOnClickListener(v -> onBackPressed());
        dataBinding.toolbarTitle.setText(getIntent().getStringExtra(ARGS.TITLE));
        viewModel.loadArticle(getIntent().getLongExtra(ARGS.ID, 0));
    }

    @Override
    public void loadFail() {
        finish();
    }
}
