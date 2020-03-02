package com.hacknife.study.app;

import android.app.Application;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import com.hacknife.study.BuildConfig;
import com.hacknife.study.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;


public class BlogApplication extends Application {

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {

            TypedValue value = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
            layout.setPrimaryColorsId(value.resourceId, android.R.color.white);
            return new BezierCircleHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setDrawableSize(20));

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("dzq")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

}
