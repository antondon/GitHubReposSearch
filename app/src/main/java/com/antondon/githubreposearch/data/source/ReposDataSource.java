package com.antondon.githubreposearch.data.source;

import com.antondon.githubreposearch.data.Repository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by anton on 11/29/17.
 */

public interface ReposDataSource {

    Flowable<List<Repository>> getRepositories(String query);

    void saveRepositories(List<Repository> repos);

    void deleteRepositories();
}
