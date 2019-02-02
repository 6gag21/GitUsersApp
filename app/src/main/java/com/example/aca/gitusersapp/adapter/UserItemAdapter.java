package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.client.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemViewHolder> {

    private List<Result.User> mData;

    public UserItemAdapter(){
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        UserItemViewHolder userItemViewHolder = new UserItemViewHolder(v);
        return userItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder userItemViewHolder, int i) {
        Result.User user = mData.get(i);

        userItemViewHolder.getmName().setText(user.name);
        userItemViewHolder.getmSurname().setText(user.surname);
        userItemViewHolder.getmUsername().setText(user.username);
        userItemViewHolder.getmImage().setImageURI(user.imageURI);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addAllUsers(Collection<Result.User> users){
        mData.addAll(users);
        notifyDataSetChanged();
    }


}
