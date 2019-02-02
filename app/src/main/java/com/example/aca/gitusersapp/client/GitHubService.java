package com.example.aca.gitusersapp.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("/users")
    Call<List<Result.User>> getUsers(@Query("since") int offset,
                                     @Query("per_page") int limit);
}
