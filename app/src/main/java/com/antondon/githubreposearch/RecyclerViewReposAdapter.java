package com.antondon.githubreposearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.antondon.githubreposearch.data.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anton on 11/29/17.
 */

public class RecyclerViewReposAdapter extends RecyclerView.Adapter<RecyclerViewReposAdapter.ViewHolder> {

    private List<Repository> mRepositories;

    public RecyclerViewReposAdapter() {
    }

    RecyclerViewReposAdapter(List<Repository> repositories) {
        this.mRepositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View repositoriesView = LayoutInflater.from(context).inflate(R.layout.card_view_repository,
                parent, false);
        return new ViewHolder(repositoriesView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mRepositories != null) {
            Repository repository = mRepositories.get(position);
            holder.textViewName.setText(repository.fullName);
            holder.textViewDescription.setText(repository.description);
            holder.textViewLanguage.setText(repository.language);
        }
    }

    @Override
    public int getItemCount() {
        return mRepositories == null ? 0 : mRepositories.size();
    }

    public void setRepositories(List<Repository> repositories) {
        this.mRepositories = repositories;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewName)
        TextView textViewName;

        @BindView(R.id.textViewDescription)
        TextView textViewDescription;

        @BindView(R.id.textViewLanguage)
        TextView textViewLanguage;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
