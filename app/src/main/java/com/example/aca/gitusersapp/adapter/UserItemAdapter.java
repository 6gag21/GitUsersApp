package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.client.Result;

import java.util.List;

public class UserItemAdapter extends RecyclerView.Adapter<UserItemViewHolder> {


    private final static String AT = "@";
    private List<Result.UsersList> mData;
    private OnItemClickListener mOnItemClickListener;

    public UserItemAdapter(List<Result.UsersList> users, OnItemClickListener onItemClickListener) {
        mData = users;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);

        return new UserItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserItemViewHolder userItemViewHolder, int i) {
        Result.UsersList user = mData.get(i);
        final int id = user.id;

        userItemViewHolder.getmUsername().setText(AT + user.username);
        Glide.with(userItemViewHolder.getmImage())
                .load(user.avatar)
                .into(userItemViewHolder.getmImage());

        userItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

   public interface OnItemClickListener {
        void onItemClicked(int id);
   }
}