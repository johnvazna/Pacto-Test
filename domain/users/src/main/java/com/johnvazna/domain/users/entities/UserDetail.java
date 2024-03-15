package com.johnvazna.domain.users.entities;

public class UserDetail extends User {
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private String twitterUsername;
    private int publicRepos;
    private int publicGists;
    private int followers;
    private int following;
    private String createdAt;
    private String updatedAt;

    public UserDetail(
            String login, int id, String node_id, String avatar_url, String gravatar_id,
            String url, String html_url, String followers_url, String gists_url,
            String starred_url, String subscriptions_url, String organizations_url,
            String repos_url, String events_url, String received_events_url, String type,
            boolean site_admin, String name, String company, String blog, String location,
            String email, String hireable, String bio, String twitterUsername,
            int publicRepos, int publicGists, int followers, int following,
            String createdAt, String updatedAt) {

        super(login, id, node_id, avatar_url, gravatar_id, url, html_url, followers_url,
                gists_url, starred_url, subscriptions_url, organizations_url, repos_url,
                events_url, received_events_url, type, site_admin);
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitterUsername = twitterUsername;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String name() {
        return name;
    }

    public String company() {
        return company;
    }

    public String blog() {
        return blog;
    }

    public String location() {
        return location;
    }

    public String email() {
        return email;
    }

    public String hireable() {
        return hireable;
    }

    public String bio() {
        return bio;
    }

    public String twitterUsername() {
        return twitterUsername;
    }

    public int publicRepos() {
        return publicRepos;
    }

    public int publicGists() {
        return publicGists;
    }

    public int followers() {
        return followers;
    }

    public int following() {
        return following;
    }

    public String createdAt() {
        return createdAt;
    }

    public String updatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
