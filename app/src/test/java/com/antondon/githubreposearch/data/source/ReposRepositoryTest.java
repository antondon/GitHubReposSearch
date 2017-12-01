package com.antondon.githubreposearch.data.source;

import android.content.Context;
import android.content.SharedPreferences;

import com.antondon.githubreposearch.data.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 11/30/17.
 */
public class ReposRepositoryTest {

    private ReposRepository mReposRepository;
    private static final String QUERY = "repo";
    private static List<Repository> REPOSITORIES = new ArrayList<>();

    @Mock
    private ReposDataSource mLocalDataSource;
    @Mock
    private ReposDataSource mRemoteDataSource;
    @Mock
    private Context mContext;
    @Mock
    private SharedPreferences mSharedPreferences;
    @Mock
    private SharedPreferences.Editor mEditor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mReposRepository = ReposRepository.getINSTANCE(mLocalDataSource, mRemoteDataSource, mContext);

        Repository firstRepo = new Repository();
        firstRepo.id = 1;
        firstRepo.fullName = "Author1/Repo1";
        firstRepo.language = "Language1";

        Repository secondRepo = new Repository();
        secondRepo.id = 2;
        secondRepo.fullName = "Author2/Repo2";

        Repository thirdRepo = new Repository();
        thirdRepo.id = 3;
        thirdRepo.fullName = "Author3/Repo3";
        thirdRepo.language = "Language3";

        REPOSITORIES.addAll(Arrays.asList(firstRepo, secondRepo, thirdRepo));

        when(mContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mSharedPreferences);
        when(mSharedPreferences.edit()).thenReturn(mEditor);
    }

    @After
    public void destroyRepositoryInstance(){
        ReposRepository.destroyInstance();
    }

    @Test
    public void getReposFromRemote_differentQuerySaveToLocalAndSaveLastQuery() throws InterruptedException {
        //last query not equal to current
        when(mSharedPreferences.getString(eq("lastQuery"), anyString())).thenReturn("not_query");
        when(mEditor.putString(anyString(), anyString())).thenReturn(mEditor);

        setRepositoriesAvailable(mRemoteDataSource);

        TestSubscriber<List<Repository>> subscriber = new TestSubscriber<>();
        mReposRepository.getRepositories(QUERY).subscribe(subscriber);
        subscriber.await();

        //For every subscriber 2 parallel requests
        verify(mRemoteDataSource, times(2)).getRepositories(QUERY);
        verify(mLocalDataSource).deleteRepositories();
        verify(mLocalDataSource).saveRepositories(REPOSITORIES);
        verify(mLocalDataSource).getRepositories(QUERY);
        verify(mEditor).putString("lastQuery", QUERY);
    }

    @Test
    public void getReposFromLocal_sameQueryNotCallRemote() throws InterruptedException {
        //last query equal current
        when(mSharedPreferences.getString(eq("lastQuery"), anyString())).thenReturn(QUERY);
        when(mEditor.putString(anyString(), anyString())).thenReturn(mEditor);

        setRepositoriesEmpty(mRemoteDataSource);
        setRepositoriesAvailable(mLocalDataSource);

        TestSubscriber<List<Repository>> subscriber1 = new TestSubscriber<>();
        mReposRepository.getRepositories(QUERY).subscribe(subscriber1);
        subscriber1.await();
        TestSubscriber<List<Repository>> subscriber2 = new TestSubscriber<>();
        mReposRepository.getRepositories(QUERY).subscribe(subscriber2);
        subscriber2.await();

        //One time for each subscriber
        verify(mLocalDataSource, times(2)).getRepositories(QUERY);
        verify(mRemoteDataSource, never()).getRepositories(QUERY);
        verify(mLocalDataSource, never()).deleteRepositories();
        verify(mLocalDataSource, never()).saveRepositories(REPOSITORIES);
        subscriber1.assertValue(REPOSITORIES);
        subscriber2.assertValue(REPOSITORIES);
    }

    void setRepositoriesAvailable(ReposDataSource dataSource) {
        when(dataSource.getRepositories(QUERY)).thenReturn(Flowable.just(REPOSITORIES));
//                .concatWith(Flowable.never()));
    }

    void setRepositoriesEmpty(ReposDataSource dataSource) {
        when(dataSource.getRepositories(QUERY)).thenReturn(Flowable.just(new ArrayList<>()));
    }

    void setRepositoriesError(ReposDataSource dataSource) {
        when(dataSource.getRepositories(QUERY)).thenReturn(Flowable.error(new Throwable()));
    }
}