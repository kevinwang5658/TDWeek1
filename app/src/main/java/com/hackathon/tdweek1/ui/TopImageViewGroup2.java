package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hackathon.tdweek1.R;
import com.yarolegovich.lovelydialog.LovelyCustomDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kwang on 2017-09-07.
 */

public class TopImageViewGroup2 extends RelativeLayout {

    public static final String TAG = "TopImageViewGroup2";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public TopImageViewGroup2(Context context) {
        super(context);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.top_image_layout, this, true);
        ButterKnife.bind(this);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.wallet_and_card_3);
        list.add(R.drawable.account_balances);
        list.add(R.drawable.weekly_spending_breakdown);

        recyclerView.setAdapter(new RecyclerAdapter(getContext(), list, true));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @OnClick(R.id.top_image) public void topImageClicked(){
        ((ViewGroup)getRootView().findViewById(R.id.root)).addView(new Popup(getContext()));
    }
}
