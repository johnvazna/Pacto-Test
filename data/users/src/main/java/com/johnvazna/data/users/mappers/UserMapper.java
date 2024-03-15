package com.johnvazna.data.users.mappers;

import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.domain.users.entities.User;

public class UserMapper {

    public static User fromDto(UserDto dto) {
        String login = dto.getLogin();
        int id = dto.getId();
        String node_id = dto.getNodeId();
        String avatar_url = dto.getAvatarUrl();
        String gravatar_id = dto.getGravatarId();
        String url = dto.getUrl();
        String html_url = dto.getHtmlUrl();
        String followers_url = dto.getFollowersUrl();
        String gists_url = dto.getGistsUrl();
        String starred_url = dto.getStarredUrl();
        String subscriptions_url = dto.getSubscriptionsUrl();
        String organizations_url = dto.getOrganizationsUrl();
        String repos_url = dto.getReposUrl();
        String events_url = dto.getEventsUrl();
        String received_events_url = dto.getReceivedEventsUrl();
        String type = dto.getType();
        boolean site_admin = dto.getSiteAdmin();

        return new User(login, id, node_id, avatar_url, gravatar_id, url, html_url, followers_url,
                gists_url, starred_url, subscriptions_url, organizations_url, repos_url, events_url,
                received_events_url, type, site_admin);
    }
}
