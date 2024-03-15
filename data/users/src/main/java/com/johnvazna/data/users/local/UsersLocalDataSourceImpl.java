package com.johnvazna.data.users.local;

import com.johnvazna.data.users.mappers.UserMapper;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.local.room.users.UsersDao;
import com.johnvazna.local.room.users.entities.UserEntity;
import com.johnvazna.network.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class UsersLocalDataSourceImpl implements UsersLocalDataSource {

    private final UsersDao usersDao;

    @Inject
    public UsersLocalDataSourceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public Single<List<User>> getAllUsers() {
        return usersDao.getAllUsers().map(entities -> {
            List<User> users = new ArrayList<>();
            for (UserEntity entity : entities) {
                users.add(UserMapper.fromEntity(entity));
            }
            return users;
        });
    }

    @Override
    public Single<List<User>> insertAndGetAllUsers(List<UserEntity> users) {
        return Completable.fromAction(() -> usersDao.insertUsers(users)).
                andThen(Single.defer(usersDao::getAllUsers)).
                compose(RxSchedulers.applySingleSchedulers()).map(entities -> {
            List<User> userList = new ArrayList<>();
            for (UserEntity entity : entities) {
                userList.add(UserMapper.fromEntity(entity));
            }
            return userList;
        });
    }
}
