package com.hacknife.study.databinding;

import android.annotation.SuppressLint;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.hacknife.study.constant.AppConfig;
import com.hacknife.study.glide.GlideApp;
import com.hacknife.study.markdown.GlideImagesPlugin;

import java.text.SimpleDateFormat;

import io.noties.markwon.Markwon;
import io.noties.markwon.ext.tables.TablePlugin;


public class DataBindingAttrSetting {
    @BindingAdapter("app:imgUrl")
    public static void setImgUrl(ImageView imageView, String url) {
        GlideApp.with(imageView)
                .load(AppConfig.APP_HOST + url)
                .into(imageView);
    }

    @SuppressLint("DefaultLocale")
    @BindingAdapter("app:textRead")
    public static void setTextRead(TextView view, long views) {
        view.setText(String.format("%d浏览", views));
    }

    @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
    @BindingAdapter("app:textTime")
    public static void setTextTime(TextView view, long time) {
        view.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
    }

    @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
    @BindingAdapter("app:textMarkdown")
    public static void setTextMarkdown(TextView view, String content) {
        if (content == null) return;
        Markwon.builder(view.getContext())
                .usePlugin(GlideImagesPlugin.create(view.getContext().getApplicationContext()))
//                .usePlugin(LinkifyPlugin.create(
//                        Linkify.EMAIL_ADDRESSES | Linkify.PHONE_NUMBERS | Linkify.WEB_URLS
//                ))
                .usePlugin(TablePlugin.create(view.getContext().getApplicationContext()))
                .build()
                .setMarkdown(view, content);
    }
}
