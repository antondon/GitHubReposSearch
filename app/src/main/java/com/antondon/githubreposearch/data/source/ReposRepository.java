package com.antondon.githubreposearch.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.antondon.githubreposearch.data.Repository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anton on 11/29/17.
 */

public class ReposRepository implements ReposDataSource {
    private static final String TAG = "ReposRepository";
    private static final String LAST_QUERY = "lastQuery";
    private static ReposRepository INSTANCE;
    private ReposDataSource mLocalDataSource;
    private ReposDataSource mRemoteDataSource;
    private Context mContext;
    private Executor mExecutor = Executors.newFixedThreadPool(2);

    private ReposRepository(ReposDataSource localDataSource, ReposDataSource remoteDataSource,
                            Context context) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
        mContext = context;
    }

    public static ReposRepository getINSTANCE(ReposDataSource localDataSource,
                                              ReposDataSource remoteDataSource, Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ReposRepository(localDataSource, remoteDataSource, context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Flowable<List<Repository>> getRepositories(String query) {
        String lastQuery = getLastQuery();
        if (!lastQuery.equals(query)) {
            //Synthetic task of requesting same data in 2 parallel threads
            Flowable<List<Repository>> firstRequest = mRemoteDataSource
                    .getRepositories(query)
                    .subscribeOn(Schedulers.from(mExecutor));

            Flowable<List<Repository>> secondRequest = mRemoteDataSource
                    .getRepositories(query)
                    .subscribeOn(Schedulers.from(mExecutor));

            Flowable<List<Repository>> mergedRequest = firstRequest
                    .mergeWith(secondRequest)
                    .firstOrError()
                    .toFlowable();

            return mergedRequest
                    .doOnNext(repos -> {
                        mLocalDataSource.deleteRepositories();
                        saveLastQuery(query);
                        mLocalDataSource.saveRepositories(repos);
                    })
                    .flatMap(__ -> mLocalDataSource.getRepositories(query));
        }
        return mLocalDataSource.getRepositories(query);
    }

    @Override
    public void saveRepositories(List<Repository> repos) {
        mLocalDataSource.saveRepositories(repos);
    }

    @Override
    public void deleteRepositories() {
        mLocalDataSource.deleteRepositories();
    }

    public void saveLastQuery(String query) {
        getSharedPreferences().edit().putString(LAST_QUERY, query).apply();
    }

    public String getLastQuery() {
        return getSharedPreferences().getString(LAST_QUERY, "");
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
    }
}
