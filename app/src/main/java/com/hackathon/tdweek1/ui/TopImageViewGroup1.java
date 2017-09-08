package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hackathon.tdweek1.R;
import com.hackathon.tdweek1.core.MainActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by kwang on 2017-09-06.
 */

public class TopImageViewGroup1 extends RelativeLayout{

    public static final String TAG = "TopImageViewGroup1";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    LovelyCustomDialog dialog;

    public TopImageViewGroup1(Context context) {
        super(context);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.top_image_layout, this, true);
        ButterKnife.bind(this);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.card_and_banner);
        list.add(R.drawable.top_repeated_purchases);
        list.add(R.drawable.weekly_spending_breakdown);


        recyclerView.setAdapter(new RecyclerAdapter(getContext(), list, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @OnClick(R.id.top_image) public void topImageClicked(){
        ((ViewGroup)getRootView().findViewById(R.id.root)).addView(new Popup(getContext()));
    }
}
