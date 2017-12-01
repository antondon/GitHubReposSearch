package com.antondon.githubreposearch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.antondon.githubreposearch.data.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


public class MainActivity extends Activity implements ReposContract.View, View.OnClickListener {

    private static final String REPOSITORIES = "repositories";
    private static final String TAG = "MainActivity";
    private static final String RECYCLER_VIEW_STATE = "recycler_view_state";
    @BindView(R.id.recyclerViewRepositories)
    public RecyclerView mRecyclerViewRepositories;
    @BindView(R.id.editTextQuery)
    public EditText mEditTextSearchQuery;
    @BindView(R.id.buttonSearchCancel)
    public Button mButtonSearchCancel;
    @BindView(R.id.textViewMessage)
    public TextView mTextViewMessage;
    private ReposContract.Presenter mPresenter;
    private List<Repository> mRepositories;
    private RecyclerViewReposAdapter mAdapter;
    private boolean mLoading = false;
    private Parcelable mRestoredState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(REPOSITORIES)) {
                mRepositories = savedInstanceState.getParcelableArrayList(REPOSITORIES);
            }
            if (savedInstanceState.containsKey(RECYCLER_VIEW_STATE)) {
                mRestoredState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE);
            }

        }
        mRecyclerViewRepositories.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewReposAdapter(mRepositories);
        mRecyclerViewRepositories.setAdapter(mAdapter);

        mButtonSearchCancel.setOnClickListener(this);

        mPresenter = new ReposPresenter(this,
                Injection.provideReposRepository(getApplicationContext()),
                Injection.provideSchedulersProvider());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mRestoredState != null) {
            mRecyclerViewRepositories.getLayoutManager().onRestoreInstanceState(mRestoredState);
        }
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void setPresenter(ReposContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showReposList(List<Repository> repos) {
        mRepositories = repos;
        mAdapter.setRepositories(mRepositories);
        mAdapter.notifyDataSetChanged();

        hideMessage();
    }

    @Override
    public void showLoadingError(Throwable throwable) {
        showMessage(getString(R.string.message_loading_error));
    }

    @Override
    public void showNoRepositories() {
        mRepositories = new ArrayList<>();
        showMessage(getString(R.string.message_no_repos));
    }

    @Override
    public void setLoadingIndicator(boolean loading) {
        mLoading = loading;
        if (mButtonSearchCancel != null) {
            mButtonSearchCancel.setText(getString(mLoading ? R.string.button_cancel : R.string.button_search));
        }
    }

    @Override
    public void onClick(View view) {
        if (mLoading) {
            mPresenter.cancelLoading();
            mLoading = false;
        } else {
            String query = mEditTextSearchQuery.getText().toString();
            if (!query.isEmpty()) {
                mPresenter.loadRepositories(query);
            } else {
                Toast.makeText(this, getString(R.string.enter_search_query), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecyclerViewRepositories != null) {
            outState.putParcelable(RECYCLER_VIEW_STATE,
                    mRecyclerViewRepositories.getLayoutManager().onSaveInstanceState());
        }
        if (mRepositories != null) {
            outState.putParcelableArrayList(REPOSITORIES, new ArrayList<>(mRepositories));
        }
    }

    void showMessage(String message) {
        if (mTextViewMessage != null && mRecyclerViewRepositories != null) {
            mRecyclerViewRepositories.setVisibility(View.GONE);
            mTextViewMessage.setVisibility(View.VISIBLE);
            mTextViewMessage.setText(message);
        }
    }

    void hideMessage() {
        if (mTextViewMessage != null && mRecyclerViewRepositories != null) {
            mRecyclerViewRepositories.setVisibility(View.VISIBLE);
            mTextViewMessage.setVisibility(View.GONE);
        }
    }
}
