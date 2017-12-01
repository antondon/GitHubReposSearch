package com.antondon.githubreposearch.schedulers;

import io.reactivex.Scheduler;

/**
 * Created by anton on 11/30/17.
 */

public interface BaseSchedulersProvider {

    Scheduler io();

    Scheduler computation();

    Scheduler ui();
}
