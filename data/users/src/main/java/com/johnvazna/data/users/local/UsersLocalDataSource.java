package com.johnvazna.data.users.local;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.local.room.users.entities.UserEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface UsersLocalDataSource {

    Single<List<User>> getAllUsers();

    Single<List<User>> insertAndGetAllUsers(List<UserEntity> users);
}
