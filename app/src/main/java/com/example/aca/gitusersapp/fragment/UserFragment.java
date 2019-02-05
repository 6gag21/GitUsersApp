package com.example.aca.gitusersapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.client.ApiManager;
import com.example.aca.gitusersapp.client.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {


    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private ImageView mImage;
    private TextView mUsername;
    private TextView mName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadUser(userId);

    }

    private void init(View view) {
        mImage = view.findViewById(R.id.fragment_image);
        mUsername = view.findViewById(R.id.fragment_username);
        mName = view.findViewById(R.id.fragment_name);
    }

    private void loadUser(int id) {
        Call<Result.User> call = ApiManager.getApiClient().getUserById(id);
        call.enqueue(new Callback<Result.User>() {
            @Override
            public void onResponse(Call<Result.User> call, Response<Result.User> response) {
                Result.User user = response.body();
                if (user != null) {
                    fill(user);
                }
            }

            @Override
            public void onFailure(Call<Result.User> call, Throwable t) {

            }
        });
    }

    private void fill(Result.User user){
        Glide.with(this).load(user.avatar).into(mImage);

        mUsername.setText("username : @" + user.username);
        mName.setText("Name : " + user.name);
    }

}
