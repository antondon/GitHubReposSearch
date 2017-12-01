package com.antondon.githubreposearch;

import com.antondon.githubreposearch.schedulers.BaseSchedulersProvider;
import com.antondon.githubreposearch.schedulers.ImmediateSchedulersProvider;
import com.antondon.githubreposearch.data.Repository;
import com.antondon.githubreposearch.data.source.ReposRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by anton on 11/30/17.
 */
public class ReposPresenterTest {

    private ReposPresenter mReposPresenter;
    private static final String QUERY = "repo";

    @Mock
    private ReposContract.View mReposView;
    @Mock
    private ReposRepository mReposRepository;

    private BaseSchedulersProvider mSchedulersProvider;

    private static List<Repository> REPOSITORIES = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mSchedulersProvider = new ImmediateSchedulersProvider();
        mReposPresenter = new ReposPresenter(mReposView, mReposRepository, mSchedulersProvider);

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
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mReposPresenter = new ReposPresenter(mReposView, mReposRepository, mSchedulersProvider);

        // Then the presenter is set to the view
        verify(mReposView).setPresenter(mReposPresenter);
    }

    @Test
    public void loadReposFromRepository_loadToView() {
        when(mReposRepository.getRepositories(QUERY)).thenReturn(Flowable.just(REPOSITORIES));

        mReposPresenter.loadRepositories(QUERY);
        verify(mReposView).setLoadingIndicator(true);
        verify(mReposView).setLoadingIndicator(false);
    }

    @Test
    public void loadReposFromEmptyRepository_showNoReposMessage() {
        when(mReposRepository.getRepositories(QUERY)).thenReturn(Flowable.just(new ArrayList<>()));

        mReposPresenter.loadRepositories(QUERY);
        verify(mReposView).showNoRepositories();
        verify(mReposView).setLoadingIndicator(false);
    }

    @Test
    public void errorLoadingRepos_showErrorMessage() {
        Throwable throwable = new Throwable();
        when(mReposRepository.getRepositories(QUERY)).thenReturn(Flowable.error(throwable));

        mReposPresenter.loadRepositories(QUERY);
        verify(mReposView).showLoadingError(throwable);
        verify(mReposView).setLoadingIndicator(false);
    }

    @Test
    public void loadFromRepositoryCancelLoading_loadToView() {
        when(mReposRepository.getRepositories(QUERY)).thenReturn(Flowable.never());

        mReposPresenter.loadRepositories(QUERY);
        mReposPresenter.cancelLoading();

        verify(mReposView).setLoadingIndicator(true);
        verify(mReposView).setLoadingIndicator(false);
    }
}