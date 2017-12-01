package com.antondon.githubreposearch.data.source.local;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.antondon.githubreposearch.data.Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.assertNotNull;

/**
 * Created by anton on 12/1/17.
 */
@RunWith(AndroidJUnit4.class)
public class ReposDataSourceLocalTest {

    private ReposDataSourceLocal reposDataSourceLocal;

    private static List<Repository> REPOSITORIES;
    private static final String QUERY = "repo";

    @Before
    public void setUp() throws Exception {
        reposDataSourceLocal = ReposDataSourceLocal.getInstance(InstrumentationRegistry.getTargetContext());
        reposDataSourceLocal.deleteRepositories();
        initRepositories();
    }

    @After
    public void tearDown() throws Exception {
        reposDataSourceLocal.destroyInstance();
        reposDataSourceLocal.deleteRepositories();
    }

    private void initRepositories() {
        if (REPOSITORIES == null || REPOSITORIES.isEmpty()) {
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

            REPOSITORIES = new ArrayList<>(Arrays.asList(firstRepo, secondRepo, thirdRepo));
        }
    }

    @Test
    public void testPreConditions() {
        assertNotNull(reposDataSourceLocal);
    }

    @Test
    public void saveRepositories_retrieveRepositories() throws InterruptedException {
        reposDataSourceLocal.saveRepositories(REPOSITORIES);
        TestSubscriber<List<Repository>> subscriber = new TestSubscriber<>();
        reposDataSourceLocal.getRepositories(QUERY)
                .firstOrError()
                .toFlowable()
                .subscribe(subscriber);
        subscriber.await();
        subscriber.assertValue(REPOSITORIES);
    }

    @Test
    public void saveRepositoriesDeleteRepositories_retrieveFromEmptySource() throws InterruptedException {
        reposDataSourceLocal.saveRepositories(REPOSITORIES);
        reposDataSourceLocal.deleteRepositories();
        TestSubscriber<List<Repository>> subscriber = new TestSubscriber<>();
        reposDataSourceLocal.getRepositories(QUERY)
                .firstOrError()
                .toFlowable()
                .subscribe(subscriber);
        subscriber.await();
        subscriber.assertValue(new ArrayList<>());
    }

}