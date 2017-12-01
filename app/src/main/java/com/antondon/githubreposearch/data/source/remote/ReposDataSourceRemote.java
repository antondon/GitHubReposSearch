package com.antondon.githubreposearch.data.source.remote;

import com.antondon.githubreposearch.data.Repository;
import com.antondon.githubreposearch.data.source.ReposDataSource;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anton on 11/29/17.
 */

public class ReposDataSourceRemote implements ReposDataSource {
    private static final String TAG = "ReposDataSourceRemote";

    private static ReposDataSourceRemote INSTANCE;

    private ReposRetrofitService mRetrofitService;

    private ReposDataSourceRemote() {
    }

    public static ReposDataSourceRemote getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ReposDataSourceRemote();
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Repository>> getRepositories(String query) {
        initRemoteSource();
        return mRetrofitService
                .getRepositories(query)
                .flatMap(resp -> Flowable.just(resp.repositories));
    }

    @Override
    public void saveRepositories(List<Repository> repos) {
        //empty
    }

    @Override
    public void deleteRepositories() {
        //empty
    }

    private void initRemoteSource() {
        if (mRetrofitService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient loggingClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.client(loggingClient)
                    .build();
            mRetrofitService = retrofit.create(ReposRetrofitService.class);
        }
    }
}
