package com.example.aca.gitusersapp.client;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    public class UsersList {
        public int id;
        @SerializedName("login")
        @Expose
        public String username;
        @SerializedName("avatar_url")
        @Expose
        public String avatar;

    }

    public class User {
        @SerializedName("avatar_url")
        @Expose
        public String avatar;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("login")
        @Expose
        public String username;
        @SerializedName("followers")
        @Expose
        public int followersCount;
        @SerializedName("html_url")
        @Expose
        public String URL;
    }

    public class UserRepos{
        @SerializedName("name")
        @Expose
        public String reposName;
    }
}
