package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kwang on 2017-09-06.
 */

public class ViewPagerAdapter extends PagerAdapter{

    public static final int NUM_PAGES = 2;

    private Context context;

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup viewGroup;
        if (position == 0) {
            viewGroup = new TopImageViewGroup1(context);
        } else {
            viewGroup = new TopImageViewGroup2(context);
        }
        container.addView(viewGroup);
        return viewGroup;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((ViewGroup) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Title";
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
