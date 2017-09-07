package com.hackathon.tdweek1.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.RecyclerView;
import android.util.Config;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.hackathon.tdweek1.BuildConfig;
import com.hackathon.tdweek1.R;
import com.hackathon.tdweek1.ui.RecyclerAdapter;
import com.hackathon.tdweek1.ui.ViewPagerAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.top_panel) ViewPager topPanel;
    @BindView(R.id.viewpagertab) SmartTabLayout viewPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    // Add the line number
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        }

        ButterKnife.bind(this);

        topPanel.setAdapter(new ViewPagerAdapter(this));
        viewPagerTab.setViewPager(topPanel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appbar, menu);

        MenuItem menuItem = menu.findItem(R.id.search);

        if (menuItem != null) {
            tintMenuIcon(MainActivity.this, menuItem, android.R.color.white);
        }

        menuItem = menu.findItem(R.id.more_options);

        if (menuItem != null) {
            tintMenuIcon(MainActivity.this, menuItem, android.R.color.white);
        }

        return true;
    }

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, context.getResources().getColor(color));

        item.setIcon(wrapDrawable);
    }
}
