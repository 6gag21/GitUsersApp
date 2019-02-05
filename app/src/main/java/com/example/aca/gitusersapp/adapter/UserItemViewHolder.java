package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aca.gitusersapp.R;

public class UserItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mUsername;
    private ImageView mImage;


    public TextView getmUsername() {
        return mUsername;
    }

    public ImageView getmImage() {
        return mImage;
    }

    public UserItemViewHolder(@NonNull View itemView) {
        super(itemView);

        mUsername = itemView.findViewById(R.id.username);
        mImage = itemView.findViewById(R.id.user_image);
    }
}
