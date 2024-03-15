package com.johnvazna.data.users.remote.entities;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class UserDetailDto extends UserDto {

    @SerializedName("name")
    private String name;
    @SerializedName("company")
    @Nullable
    private String company;
    @SerializedName("blog")
    private String blog;
    @SerializedName("location")
    @Nullable
    private String location;
    @SerializedName("email")
    @Nullable
    private String email;
    @SerializedName("hireable")
    @Nullable
    private String hireable;
    @SerializedName("bio")
    @Nullable
    private String bio;
    @SerializedName("twitter_username")
    @Nullable
    private String twitter_username;
    @SerializedName("public_repos")
    private int public_repos;
    @SerializedName("public_gists")
    private int public_gists;
    @SerializedName("followers")
    private int followers;
    @SerializedName("following")
    private int following;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public String name() {
        return name;
    }

    @Nullable
    public String company() {
        return company;
    }

    public String blog() {
        return blog;
    }

    @Nullable
    public String location() {
        return location;
    }

    @Nullable
    public String email() {
        return email;
    }

    @Nullable
    public String hireable() {
        return hireable;
    }

    @Nullable
    public String bio() {
        return bio;
    }

    @Nullable
    public String twitter_username() {
        return twitter_username;
    }

    public int public_repos() {
        return public_repos;
    }

    public int public_gists() {
        return public_gists;
    }

    public int followers() {
        return followers;
    }

    public int following() {
        return following;
    }

    public String created_at() {
        return created_at;
    }

    public String updated_at() {
        return updated_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(@Nullable String company) {
        this.company = company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setLocation(@Nullable String location) {
        this.location = location;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setHireable(@Nullable String hireable) {
        this.hireable = hireable;
    }

    public void setBio(@Nullable String bio) {
        this.bio = bio;
    }

    public void setTwitter_username(@Nullable String twitter_username) {
        this.twitter_username = twitter_username;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
