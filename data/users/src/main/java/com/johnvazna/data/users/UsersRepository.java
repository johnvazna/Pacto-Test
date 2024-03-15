package com.johnvazna.data.users;

import com.johnvazna.data.users.local.UsersLocalDataSource;
import com.johnvazna.data.users.mappers.UserMapper;
import com.johnvazna.data.users.remote.UsersRemoteDataSource;
import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.local.room.users.entities.UserEntity;
import com.johnvazna.network.RxSchedulers;
import com.johnvazna.network.conectivity.ConnectivityService;
import com.johnvazna.network.utils.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UsersRepository implements UsersDataSource {

    private final ConnectivityService connectivityService;
    private final UsersLocalDataSource usersLocalDataSource;
    private final UsersRemoteDataSource usersRemoteDataSource;

    @Inject
    public UsersRepository(
            ConnectivityService connectivityService,
            UsersLocalDataSource usersLocalDataSource,
            UsersRemoteDataSource usersRemoteDataSource) {
        this.connectivityService = connectivityService;
        this.usersLocalDataSource = usersLocalDataSource;
        this.usersRemoteDataSource = usersRemoteDataSource;
    }

    @Override
    public Single<Result<List<User>>> getUsers() {
        if (connectivityService.isOnline()) {
            return usersRemoteDataSource.getUsers()
                    .flatMap(result -> {
                        if (result.isSuccess()) {
                            List<UserEntity> entities = new ArrayList<>();
                            for (UserDto dto : result.getData()) {
                                entities.add(UserMapper.dtoToEntity(dto));
                            }
                            return usersLocalDataSource.insertAndGetAllUsers(entities)
                                    .compose(RxSchedulers.applySingleSchedulers())
                                    .map(Result::success);
                        } else {
                            // Attempt to recover local data if the remote request failed
                            return usersLocalDataSource.getAllUsers()
                                    .compose(RxSchedulers.applySingleSchedulers())
                                    .map(Result::success);
                        }
                    });
        } else {
            return usersLocalDataSource.getAllUsers()
                    .compose(RxSchedulers.applySingleSchedulers())
                    .map(Result::success);
        }
    }

    @Override
    public Single<Result<UserDetail>> getUserByUsername(String name) {
        return usersRemoteDataSource.getUserByUsername(name)
                .map(result -> {
                    if (result.isSuccess()) {
                        UserDetail userDetail = UserMapper.fromDetailDto(result.getData());
                        return Result.success(userDetail);
                    } else {
                        return Result.error(result.getError());
                    }
                });
    }
}
