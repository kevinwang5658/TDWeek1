package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hackathon.tdweek1.R;
import com.rievo.android.library.BackStackManager;
import com.rievo.android.library.LinearBackStack;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by kwang on 2017-09-07.
 */

public class Reward extends RelativeLayout {

    @BindView(R.id.reward_img) ImageView imageView;

    public Reward(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.reward, this, true);

        ButterKnife.bind(this);

        GlideApp.with(getContext())
                .asGif()
                .load(R.raw.success_screen)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView);

        Single.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        BackStackManager.getBackStackManager().goBack();
                    }
                });
    }
}
