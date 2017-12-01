package com.antondon.githubreposearch.data.source.local;

import android.content.Context;

import com.antondon.githubreposearch.data.Repository;
import com.antondon.githubreposearch.data.source.ReposDataSource;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by anton on 11/29/17.
 */

public class ReposDataSourceLocal implements ReposDataSource {

    private static ReposDataSourceLocal INSTANCE;
    private Context mContext;

    private ReposDataSourceLocal(Context context) {
        mContext = context;
    }

    public static ReposDataSourceLocal getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ReposDataSourceLocal(context);
        }
        return INSTANCE;
    }


    public void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Flowable<List<Repository>> getRepositories(String query) {
        return getDatabaseInstance().repoDao().getRepositories();
    }

    @Override
    public void saveRepositories(List<Repository> repos) {
        getDatabaseInstance().repoDao().saveRepositories(repos);
    }

    @Override
    public void deleteRepositories() {
        getDatabaseInstance().repoDao().deleteRepositories();
    }

    private RepositoriesDatabase getDatabaseInstance() {
        return RepositoriesDatabase.getInstance(mContext);
    }
}
