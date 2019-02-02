package com.example.aca.gitusersapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aca.gitusersapp.adapter.UserItemAdapter;
import com.example.aca.gitusersapp.client.ApiManager;
import com.example.aca.gitusersapp.client.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   UserItemAdapter userItemAdapter;

    List<Result.User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = findViewById(R.id.rv_main);
        loadUsers();
        userItemAdapter = new UserItemAdapter();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(userItemAdapter);
        userItemAdapter.addAllUsers(users);
    }

    private void loadUsers() {

            Call<List<Result.User>> call = ApiManager.getApiClient().getUsers(0, 10);
            call.enqueue(new Callback<List<Result.User>>() {
                @Override
                public void onResponse(Call<List<Result.User>> call, Response<List<Result.User>> response) {
                     users = response.body();
                }

                @Override
                public void onFailure(Call<List<Result.User>> call, Throwable t) {

                }
            });
    }

}
