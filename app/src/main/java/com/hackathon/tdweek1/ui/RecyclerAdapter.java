package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.hackathon.tdweek1.R;
import com.hackathon.tdweek1.core.MainActivity;
import com.rievo.android.library.LinearBackStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import timber.log.Timber;

/**
 * Created by kwang on 2017-09-06.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Integer> list;
    private boolean isTopImageView2;
    private boolean toggle = false;

    RecyclerAdapter(Context context, List<Integer> drawableIds, boolean isTopImageView2){
        this.context = context;
        this.list = drawableIds;
        this.isTopImageView2 = isTopImageView2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notcardview, parent, false);
        } else if (viewType == 2 && isTopImageView2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.longview, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        }

        if (viewType == 2 && !isTopImageView2){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!toggle){
                        list.clear();
                        list.add(R.drawable.red_banner_and_card);
                        list.add(R.drawable.get_back_on_track);
                        list.add(R.drawable.top_repeated_purchases);
                        list.add(R.drawable.weekly_spending_breakdown);
                        notifyDataSetChanged();
                        ((RecyclerView) view.getParent()).smoothScrollToPosition(0);
                        toggle = true;
                    } else {
                        list.clear();
                        list.add(R.drawable.card_and_banner);
                        list.add(R.drawable.top_repeated_purchases);
                        list.add(R.drawable.weekly_spending_breakdown);
                        notifyDataSetChanged();
                        ((RecyclerView) view.getParent()).smoothScrollToPosition(0);
                        toggle = false;
                    }
                }
            });
        }

        if (viewType == 2 && isTopImageView2){
            view.findViewById(R.id.image_long).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).setTitle("Transaction History");
                    LinearBackStack.get(TopImageViewGroup2.TAG)
                            .addIndependentView((ViewGroup) parent.getRootView().findViewById(R.id.root), new Creator())
                            .done();
                }
            });
        }

        if (viewType == 0 && !isTopImageView2){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).getSupportActionBar().hide();
                    LinearBackStack.get(TopImageViewGroup1.TAG)
                            .addIndependentView((ViewGroup) parent.getRootView().findViewById(R.id.root), new Creator1())
                            .done();
                }
            });
        }

        if (!(viewType == 2 && isTopImageView2)) {
            return new ViewHolder(view);
        } else {
            return new ViewHolder1(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (!(position == 2 && isTopImageView2)) {
            ViewHolder viewHolder = (ViewHolder) holder;

            GlideApp.with(context)
                    .load(list.get(position))
                    .into(viewHolder.imageView);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Creator implements LinearBackStack.ViewCreator{

        @Override
        public ViewGroup create(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            return new TransactionAnalysis(layoutInflater.getContext());
        }
    }

    public static class Creator1 implements LinearBackStack.ViewCreator{

        @Override
        public ViewGroup create(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            return new Reward(layoutInflater.getContext());
        }
    }
}
