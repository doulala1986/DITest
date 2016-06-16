package com.ctsi.di.test.rxlifecycle;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * Created by doulala on 16/6/14.
 */
public class Activity_LifeCycle extends RxAppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable.interval(5,3, TimeUnit.SECONDS).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                Log.e("observable","unsubscribed");
            }
        }).compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e("interval","interval");
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.e("lifecycle","onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifecycle","onPause");
    }



    @Override
    protected void onStop() {
        super.onStop();

        Log.e("lifecycle","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle","onDestroy");
    }
}
