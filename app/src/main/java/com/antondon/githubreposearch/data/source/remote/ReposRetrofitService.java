package com.antondon.githubreposearch.data.source.remote;

import com.antondon.githubreposearch.data.ReposResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anton on 11/29/17.
 */

public interface ReposRetrofitService {

    @GET("/search/repositories")
    Flowable<ReposResponse> getRepositories(@Query("q") String query);
}
