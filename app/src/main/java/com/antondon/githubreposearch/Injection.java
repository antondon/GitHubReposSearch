package com.antondon.githubreposearch;

import android.content.Context;

import com.antondon.githubreposearch.data.source.ReposRepository;
import com.antondon.githubreposearch.data.source.local.ReposDataSourceLocal;
import com.antondon.githubreposearch.data.source.remote.ReposDataSourceRemote;
import com.antondon.githubreposearch.schedulers.SchedulersProvider;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Enables injection of mock implementations for
 * {@link com.antondon.githubreposearch.data.source.ReposDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static ReposRepository provideReposRepository(Context context) {
        return ReposRepository.getINSTANCE(ReposDataSourceLocal.getInstance(checkNotNull(context)),
                ReposDataSourceRemote.getINSTANCE(), context);
    }

    public static SchedulersProvider provideSchedulersProvider() {
        return SchedulersProvider.getInstance();
    }
}
