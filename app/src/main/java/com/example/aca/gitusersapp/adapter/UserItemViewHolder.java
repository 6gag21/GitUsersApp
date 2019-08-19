package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aca.gitusersapp.R;

 class UserItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mUsername;
    private ImageView mImage;



     TextView getUsername() {
        return mUsername;
    }

     ImageView getImage() {
        return mImage;
    }

     UserItemViewHolder(@NonNull View itemView) {
        super(itemView);

        mUsername = itemView.findViewById(R.id.username);
        mImage = itemView.findViewById(R.id.user_image);
    }
}
