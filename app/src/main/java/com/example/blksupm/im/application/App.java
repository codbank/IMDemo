package com.example.blksupm.im.application;

import android.app.Application;
import io.rong.imkit.RongIM;

/**
 * Created by blksupm on 2016/11/23.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}
