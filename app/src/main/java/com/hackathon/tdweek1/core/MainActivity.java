package com.hackathon.tdweek1.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.rievo.android.library.BackStackManager;
import com.rievo.android.library.ClusterBackStack;

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

    public static final String ROOT_BACKSTACK = "root_backstack";

    @BindView(R.id.top_panel) ViewPager topPanel;
    @BindView(R.id.viewpagertab) SmartTabLayout viewPagerTab;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;
    ClusterBackStack clusterBackStack;

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
        BackStackManager.install(this);
        clusterBackStack = ClusterBackStack.create(ROOT_BACKSTACK, 0);
        BackStackManager.setRootBackStack(clusterBackStack);

        ButterKnife.bind(this);

        topPanel.setAdapter(new ViewPagerAdapter(this, clusterBackStack));
        viewPagerTab.setViewPager(topPanel);
        topPanel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clusterBackStack.switchContext(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
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

    @Override
    public void onBackPressed() {
        BackStackManager.getBackStackManager().goBack();
        getSupportActionBar().show();
        setTitle(getResources().getString(R.string.app_name));
    }

    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, context.getResources().getColor(color));

        item.setIcon(wrapDrawable);
    }
}
