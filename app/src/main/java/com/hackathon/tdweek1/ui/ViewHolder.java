package com.hackathon.tdweek1.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.hackathon.tdweek1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kwang on 2017-09-06.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_card) ImageView imageView;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
