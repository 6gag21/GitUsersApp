package com.example.aca.gitusersapp.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("users")
    Call<List<Result.UsersList>> getUsers(@Query("since") int offset,
                                          @Query("per_page") int limit);

    @GET("user/{id}")
    Call<Result.User> getUserById(@Path("id") int id);

    @GET("user/{id}/repos")
    Call<List<Result.UserRepos>> getUserRepos(@Path("id") int id);
}
