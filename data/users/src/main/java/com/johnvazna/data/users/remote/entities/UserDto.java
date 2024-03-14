package com.johnvazna.data.users.remote.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDto implements Serializable {

    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private int id;
    @SerializedName("node_id")
    private String node_id;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("gravatar_id")
    private String gravatar_id;
    @SerializedName("url")
    private String url;
    @SerializedName("html_url")
    private String html_url;
    @SerializedName("followers_url")
    private String followers_url;
    @SerializedName("gists_url")
    private String gists_url;
    @SerializedName("starred_url")
    private String starred_url;
    @SerializedName("subscriptions_url")
    private String subscriptions_url;
    @SerializedName("organizations_url")
    private String organizations_url;
    @SerializedName("repos_url")
    private String repos_url;
    @SerializedName("events_url")
    private String events_url;
    @SerializedName("received_events_url")
    private String received_events_url;
    @SerializedName("type")
    private String type;
    @SerializedName("site_admin")
    private boolean site_admin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNodeId() {
        return node_id;
    }

    public void setNodeId(String nodeId) {
        this.node_id = nodeId;
    }

    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatar_url = avatarUrl;
    }

    public String getGravatarId() {
        return gravatar_id;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatar_id = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.html_url = htmlUrl;
    }

    public String getFollowersUrl() {
        return followers_url;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followers_url = followersUrl;
    }

    public String getGistsUrl() {
        return gists_url;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gists_url = gistsUrl;
    }

    public String getStarredUrl() {
        return starred_url;
    }

    public void setStarredUrl(String starredUrl) {
        this.starred_url = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptions_url;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptions_url = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizations_url;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizations_url = organizationsUrl;
    }

    public String getReposUrl() {
        return repos_url;
    }

    public void setReposUrl(String reposUrl) {
        this.repos_url = reposUrl;
    }

    public String getEventsUrl() {
        return events_url;
    }

    public void setEventsUrl(String eventsUrl) {
        this.events_url = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return received_events_url;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.received_events_url = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getSiteAdmin() {
        return site_admin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.site_admin = siteAdmin;
    }
}
