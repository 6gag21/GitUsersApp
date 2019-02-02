package com.example.aca.gitusersapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aca.gitusersapp.R;

public class UserItemViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mSurname;
    private TextView mUsername;
    private ImageView mImage;

    public TextView getmName() {
        return mName;
    }

    public void setmName(TextView mName) {
        this.mName = mName;
    }

    public TextView getmSurname() {
        return mSurname;
    }

    public void setmSurname(TextView mSurname) {
        this.mSurname = mSurname;
    }

    public TextView getmUsername() {
        return mUsername;
    }

    public void setmUsername(TextView mUsername) {
        this.mUsername = mUsername;
    }

    public ImageView getmImage() {
        return mImage;
    }

    public void setmImage(ImageView mImage) {
        this.mImage = mImage;
    }

    public UserItemViewHolder(@NonNull View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.user_name);
        mSurname = itemView.findViewById(R.id.user_surname);
        mUsername = itemView.findViewById(R.id.username);
        mImage = itemView.findViewById(R.id.user_image);
    }
}
