package com.hackathon.tdweek1.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hackathon.tdweek1.R;

import static junit.runner.Version.id;

/**
 * Created by kwang on 2017-09-07.
 */

public class TransactionAnalysis extends RelativeLayout{

    public TransactionAnalysis(Context context) {
        super(context);
        setId(123456);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.transaction, this, true);
    }
}
