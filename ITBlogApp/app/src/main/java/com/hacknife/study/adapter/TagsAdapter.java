package com.hacknife.study.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hacknife.study.R;
import com.hacknife.study.entity.ITag;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.ArrayList;
import java.util.List;

public class TagsAdapter<T extends ITag> extends TagAdapter<T> {
    List<T> tags;

    public TagsAdapter() {
        this(new ArrayList<>());
    }

    public TagsAdapter(List<T> datas) {
        super(datas);
        tags = datas;
    }

    public void bindData(List<T> tags) {
        this.tags.addAll(tags);
        notifyDataChanged();
    }

    @Override
    public View getView(FlowLayout parent, int position, T tag) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag,
                parent, false);
        textView.setText(tag.getName());
        return textView;
    }

    public  T  getTag(int index) {
        return tags.get(index);
    }
}
