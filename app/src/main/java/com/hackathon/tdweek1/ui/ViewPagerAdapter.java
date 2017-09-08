package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rievo.android.library.BackStackManager;
import com.rievo.android.library.ClusterBackStack;
import com.rievo.android.library.LinearBackStack;

/**
 * Created by kwang on 2017-09-06.
 */

public class ViewPagerAdapter extends PagerAdapter{

    public static final int NUM_PAGES = 2;

    private Context context;
    private ClusterBackStack clusterBackStack;

    public ViewPagerAdapter(Context context, ClusterBackStack clusterBackStack){
        this.context = context;
        this.clusterBackStack = clusterBackStack;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup viewGroup;
        if (position == 0) {
            viewGroup = LinearBackStack.create(TopImageViewGroup1.TAG, container, new Creator1());
            clusterBackStack.add(LinearBackStack.get(TopImageViewGroup1.TAG));
        } else {
            viewGroup = LinearBackStack.create(TopImageViewGroup2.TAG, container, new Creator2());
            clusterBackStack.add(LinearBackStack.get(TopImageViewGroup2.TAG));
        }
        return viewGroup;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        container.removeView((ViewGroup) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Piggy Bank";
        } else {
            return "Wallet";
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public static class Creator1 implements LinearBackStack.ViewCreator{

        @Override
        public ViewGroup create(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            TopImageViewGroup1 viewGroup1 = new TopImageViewGroup1(layoutInflater.getContext());
            viewGroup.addView(viewGroup1);
            return viewGroup1;
        }
    }

    public static class Creator2 implements LinearBackStack.ViewCreator{

        @Override
        public ViewGroup create(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            TopImageViewGroup2 viewGroup1 = new TopImageViewGroup2(layoutInflater.getContext());
            viewGroup.addView(viewGroup1);
            return viewGroup1;
        }
    }

}
