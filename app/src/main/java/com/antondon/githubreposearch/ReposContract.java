package com.antondon.githubreposearch;

import com.antondon.githubreposearch.data.Repository;

import java.util.List;

/**
 * Created by anton on 11/29/17.
 */

public interface ReposContract {

    interface View extends BaseView<Presenter> {

        void showReposList(List<Repository> repositories);

        void showLoadingError(Throwable throwable);

        void showNoRepositories();

        void setLoadingIndicator(boolean loading);
    }

    interface Presenter extends BasePresenter {

        void loadRepositories(String query);

        void cancelLoading();
    }
}
