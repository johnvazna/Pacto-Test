package com.johnvazna.local.room.users.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "node_id")
    private String nodeId;

    @ColumnInfo(name = "avatar_url")
    private String avatarUrl;

    @ColumnInfo(name = "gravatar_id")
    private String gravatarId;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "html_url")
    private String htmlUrl;

    @ColumnInfo(name = "followers_url")
    private String followersUrl;

    @ColumnInfo(name = "gists_url")
    private String gistsUrl;

    @ColumnInfo(name = "starred_url")
    private String starredUrl;

    @ColumnInfo(name = "subscriptions_url")
    private String subscriptionsUrl;

    @ColumnInfo(name = "organizations_url")
    private String organizationsUrl;

    @ColumnInfo(name = "repos_url")
    private String reposUrl;

    @ColumnInfo(name = "events_url")
    private String eventsUrl;

    @ColumnInfo(name = "received_events_url")
    private String receivedEventsUrl;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "site_admin")
    private boolean siteAdmin;

    public UserEntity(int id, String login, String nodeId, String avatarUrl, String gravatarId,
                      String url, String htmlUrl, String followersUrl, String gistsUrl,
                      String starredUrl, String subscriptionsUrl, String organizationsUrl,
                      String reposUrl, String eventsUrl, String receivedEventsUrl,
                      String type, boolean siteAdmin) {
        this.id = id;
        this.login = login;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String nodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String avatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String gravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String url() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String htmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String followersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String gistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String starredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String subscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String organizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String reposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String eventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String receivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean siteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }
}
