package com.antondon.githubreposearch.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.antondon.githubreposearch.data.Repository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by anton on 11/29/17.
 */

@Dao
public interface RepositoriesDao {

    @Query("SELECT * FROM repositories")
    Flowable<List<Repository>> getRepositories();

    @Query("DELETE FROM Repositories")
    void deleteRepositories();

    @Insert
    void saveRepositories(List<Repository> repositories);
}
