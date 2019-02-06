package com.example.aca.gitusersapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.adapter.FragmentReposAdapter;
import com.example.aca.gitusersapp.client.ApiManager;
import com.example.aca.gitusersapp.client.Result;
import com.example.aca.gitusersapp.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {

    private String URL;

    private View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
                  mWebView.loadUrl(URL);
        }
    };

    private FragmentReposAdapter mFragmentReposAdapter;
    private ArrayList<Result.UserRepos> mReposList;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private ImageView mOpenWebView;
    private WebView mWebView;
    private ImageView mImage;
    private TextView mUsername;
    private TextView mName;
    private TextView mFollowers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_user, container, false);
        v.setOnClickListener(null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mReposList = new ArrayList<>();
        init(view);
        loadUser(userId);
        initRecyclerView(view);
        loadRepos(userId);

    }

    private void init(View view) {
        mImage = view.findViewById(R.id.fragment_image);
        mUsername = view.findViewById(R.id.fragment_username);
        mName = view.findViewById(R.id.fragment_name);
        mFollowers = view.findViewById(R.id.fragment_followers);
        mWebView = view.findViewById(R.id.fragment_web_view);
        mOpenWebView = view.findViewById(R.id.fragment_open_web_view);
        mOpenWebView.setOnClickListener(onClickListener);
    }

    private void loadUser(int id) {
        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            Call<Result.User> call = ApiManager.getApiClient().getUserById(id);
            call.enqueue(new Callback<Result.User>() {
                @Override
                public void onResponse(Call<Result.User> call, Response<Result.User> response) {
                    Result.User user = response.body();
                    if (user != null) {
                        fill(user);
                        URL = user.URL;
                    }
                }

                @Override
                public void onFailure(Call<Result.User> call, Throwable t) {

                }
            });
        }
    }

    private void loadRepos(int id) {
        if (NetworkUtils.isNetworkAvailable(getActivity())) {
            Call<List<Result.UserRepos>> call = ApiManager.getApiClient().getUserRepos(id);
            call.enqueue(new Callback<List<Result.UserRepos>>() {
                @Override
                public void onResponse(Call<List<Result.UserRepos>> call, Response<List<Result.UserRepos>> response) {
                    List<Result.UserRepos> repos = response.body();
                    if (repos != null) {
                        mReposList.addAll(repos);
                        mFragmentReposAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Result.UserRepos>> call, Throwable t) {

                }
            });
        }
    }

    private void fill(Result.User user){
        Glide.with(this).load(user.avatar).into(mImage);

        mUsername.setText("username : @" + user.username);
        mName.setText("Name : " + user.name);
        mFollowers.setText("Followers : " + user.followersCount);
    }

    private void initRecyclerView(View view){
        mFragmentReposAdapter = new FragmentReposAdapter(mReposList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView mRecyclerView = view.findViewById(R.id.fragment_rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mFragmentReposAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }
}
