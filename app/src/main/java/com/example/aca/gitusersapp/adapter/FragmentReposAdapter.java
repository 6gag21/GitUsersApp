package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.client.Result;

import java.util.List;

public class FragmentReposAdapter extends RecyclerView.Adapter<FragmentReposViewHolder> {

    List<Result.UserRepos> mData;

    public FragmentReposAdapter(List<Result.UserRepos> repos){
        mData = repos;
    }

    @NonNull
    @Override
    public FragmentReposViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_repos, viewGroup,false);
        return new FragmentReposViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentReposViewHolder fragmentReposViewHolder, int i) {
        Result.UserRepos repos = mData.get(i);
        fragmentReposViewHolder.getReposName().setText(repos.reposName);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
