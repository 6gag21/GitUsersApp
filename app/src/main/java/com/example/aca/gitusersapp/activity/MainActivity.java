package com.example.aca.gitusersapp.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.example.aca.gitusersapp.R;
import com.example.aca.gitusersapp.adapter.CustomScrollListener;
import com.example.aca.gitusersapp.adapter.UserItemAdapter;
import com.example.aca.gitusersapp.client.ApiManager;
import com.example.aca.gitusersapp.client.Result;
import com.example.aca.gitusersapp.utils.NetworkUtils;
import com.example.aca.gitusersapp.fragment.UserFragment;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements UserItemAdapter.OnItemClickListener, SearchView.OnQueryTextListener {

   private UserItemAdapter userItemAdapter;
   private CustomScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUsers(0);
        initRecyclerView();
    }


    private void initRecyclerView(){
        userItemAdapter = new UserItemAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        scrollListener = new CustomScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                loadUsers(userItemAdapter.getLastUserId());
            }
        };
        RecyclerView mRecyclerView = findViewById(R.id.rv_main);
        mRecyclerView.addOnScrollListener(scrollListener);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(userItemAdapter);
    }

    private void loadUsers(int page) {
        if (NetworkUtils.isNetworkAvailable(this)) {
            Call<List<Result.UsersList>> call = ApiManager.getApiClient().getUsers(page,  10);
            call.enqueue(new Callback<List<Result.UsersList>>() {
                @Override
                public void onResponse(Call<List<Result.UsersList>> call, Response<List<Result.UsersList>> response) {
                    List<Result.UsersList> users = response.body();
                    if (users != null) {
                        userItemAdapter.addItems(users);
                        scrollListener.setLoading(false);
                    }else{
                        scrollListener.setLastPage(true);
                    }
                }

                @Override
                public void onFailure(Call<List<Result.UsersList>> call, Throwable t) {
                    Log.v("TAG", "Failure : " + t.toString());
                }
            });
        } else {
            Log.v("TAG", "No network connection");
        }
    }



    private void searchUsers(String query) {
        if (NetworkUtils.isNetworkAvailable(this)) {
            Call<Result.FoundUsers> call = ApiManager.getApiClient().searchUsers(query);
            call.enqueue(new Callback<Result.FoundUsers>() {
                @Override
                public void onResponse(Call<Result.FoundUsers> call, Response<Result.FoundUsers> response) {
                    Result.FoundUsers users = response.body();
                    if (users != null) {
                        userItemAdapter.clearData();
                        userItemAdapter.addItems(users.usersList);
                    }
                }

                @Override
                public void onFailure(Call<Result.FoundUsers> call, Throwable t) {
                    Log.v("TAG", "Failure : " + t.toString());
                }
            });
        } else {
            Log.v("TAG", "No network connection");
        }
    }

    @Override
    public void onItemClicked(int id) {
        openFragmentInContainer(id);
    }

    private void openFragmentInContainer(int id){
        UserFragment userFragment = new UserFragment();
        userFragment.setUserId(id);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_main, userFragment);
        fragmentTransaction.addToBackStack(userFragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public boolean onQueryTextChange(String query) {
        searchUsers(query);

        userItemAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

}
