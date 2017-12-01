package com.antondon.githubreposearch.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anton on 11/30/17.
 */

public class SchedulersProvider implements BaseSchedulersProvider {

    private static SchedulersProvider INSTANCE;

    private SchedulersProvider() {
    }

    public static SchedulersProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulersProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
