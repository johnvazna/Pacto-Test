package com.johnvazna.data.users.mappers;

import com.johnvazna.data.users.remote.entities.UserDetailDto;
import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.local.room.users.entities.UserEntity;

public class UserMapper {

    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getLogin(),
                entity.getId(),
                entity.getNodeId(),
                entity.getAvatarUrl(),
                entity.getGravatarId(),
                entity.getUrl(),
                entity.getHtmlUrl(),
                entity.getFollowersUrl(),
                entity.getGistsUrl(),
                entity.getStarredUrl(),
                entity.getSubscriptionsUrl(),
                entity.getOrganizationsUrl(),
                entity.getReposUrl(),
                entity.getEventsUrl(),
                entity.getReceivedEventsUrl(),
                entity.getType(),
                entity.getSiteAdmin()
        );
    }

    public static UserEntity dtoToEntity(UserDto dto) {
        return new UserEntity(
                dto.getId(),
                dto.getLogin(),
                dto.getNodeId(),
                dto.getAvatarUrl(),
                dto.getGravatarId(),
                dto.getUrl(),
                dto.getHtmlUrl(),
                dto.getFollowersUrl(),
                dto.getGistsUrl(),
                dto.getStarredUrl(),
                dto.getSubscriptionsUrl(),
                dto.getOrganizationsUrl(),
                dto.getReposUrl(),
                dto.getEventsUrl(),
                dto.getReceivedEventsUrl(),
                dto.getType(),
                dto.getSiteAdmin()
        );
    }

    public static UserDetail fromDetailDto(UserDetailDto dto) {
        return new UserDetail(
                dto.getLogin(),
                dto.getId(),
                dto.getNodeId(),
                dto.getAvatarUrl(),
                dto.getGravatarId(),
                dto.getUrl(),
                dto.getHtmlUrl(),
                dto.getFollowersUrl(),
                dto.getGistsUrl(),
                dto.getStarredUrl(),
                dto.getSubscriptionsUrl(),
                dto.getOrganizationsUrl(),
                dto.getReposUrl(),
                dto.getEventsUrl(),
                dto.getReceivedEventsUrl(),
                dto.getType(),
                dto.getSiteAdmin(),
                dto.name(),
                dto.company(),
                dto.blog(),
                dto.location(),
                dto.email(),
                dto.hireable(),
                dto.bio(),
                dto.twitter_username(),
                dto.public_repos(),
                dto.public_gists(),
                dto.followers(),
                dto.following(),
                dto.created_at(),
                dto.updated_at()
        );
    }
}
