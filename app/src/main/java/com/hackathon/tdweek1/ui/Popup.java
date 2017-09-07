package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.hackathon.tdweek1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by kwang on 2017-09-06.
 */

public class Popup extends RelativeLayout {

    public Popup(Context context) {
        super(context);

        View layout = LayoutInflater.from(getContext()).inflate(R.layout.popup, this, true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close_icon) public void close(){
        ((ViewGroup) getRootView().findViewById(R.id.root)).removeView(this);
    }

}
