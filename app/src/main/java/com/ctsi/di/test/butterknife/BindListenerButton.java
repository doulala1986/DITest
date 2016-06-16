package com.ctsi.di.test.butterknife;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by doulala on 16/5/5.
 */
public class BindListenerButton extends Button {


    public BindListenerButton(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    @OnClick
    public void click(){

        Log.e("click","click");

    }

}
