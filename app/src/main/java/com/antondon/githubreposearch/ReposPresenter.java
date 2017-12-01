package com.antondon.githubreposearch;

import com.antondon.githubreposearch.data.Repository;
import com.antondon.githubreposearch.data.source.ReposRepository;
import com.antondon.githubreposearch.schedulers.BaseSchedulersProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by anton on 11/29/17.
 */

public class ReposPresenter implements ReposContract.Presenter {
    private static final String TAG = "ReposPresenter";

    private ReposContract.View mReposView;
    private BaseSchedulersProvider mSchedulersProvider;
    private CompositeDisposable mCompositeDisposable;
    private ReposRepository mRepository;
    private boolean mLoading = false;

    public ReposPresenter(ReposContract.View reposView,
                          ReposRepository repository,
                          BaseSchedulersProvider schedulersProvider) {
        mReposView = reposView;
        mRepository = repository;
        mSchedulersProvider = schedulersProvider;

        mCompositeDisposable = new CompositeDisposable();
        mReposView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        //empty
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void loadRepositories(String query) {
        mReposView.setLoadingIndicator(true);
        mCompositeDisposable.clear();
        Disposable disposable = mRepository
                .getRepositories(query)
                .subscribeOn(mSchedulersProvider.io())
                .observeOn(mSchedulersProvider.ui())
//                .doFinally(() -> {
//                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
//                        EspressoIdlingResource.decrement(); // Set app as idle.
//                    }
//                })
                .doOnSubscribe(__ -> mLoading = true)
                .subscribe(
                        // onNext
                        repos -> {
                            mCompositeDisposable.clear();
                            processRepositories(repos);
                            mLoading = false;
                            mReposView.setLoadingIndicator(false);
                        },

                        // onError
                        throwable -> {
                            mReposView.showLoadingError(throwable);
                            mLoading = false;
                            mReposView.setLoadingIndicator(false);
                        });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void cancelLoading() {
        mCompositeDisposable.clear();
        if (mLoading) {
            mReposView.setLoadingIndicator(false);
            mLoading = false;
        }
    }

    private void processRepositories(List<Repository> repositories) {
        if (repositories == null || repositories.isEmpty()) {
            mReposView.showNoRepositories();
        } else {
            mReposView.showReposList(repositories);
        }
    }
}
