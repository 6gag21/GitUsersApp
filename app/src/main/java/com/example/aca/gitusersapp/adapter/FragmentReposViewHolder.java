package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aca.gitusersapp.R;

public class FragmentReposViewHolder extends RecyclerView.ViewHolder {

    private TextView mReposName;

    public TextView getReposName() {
        return mReposName;
    }

    public FragmentReposViewHolder(@NonNull View itemView) {
        super(itemView);
        mReposName = itemView.findViewById(R.id.repos_name);
    }


}
