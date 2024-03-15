package com.johnvazna.data.users;

import com.johnvazna.data.users.mappers.UserMapper;
import com.johnvazna.data.users.remote.UsersRemoteDataSource;
import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.network.utils.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UsersRepository implements UsersDataSource {

    private final UsersRemoteDataSource usersRemoteDataSource;

    @Inject
    public UsersRepository(UsersRemoteDataSource usersRemoteDataSource) {
        this.usersRemoteDataSource = usersRemoteDataSource;
    }

    @Override
    public Single<Result<List<User>>> getUsers() {
        return usersRemoteDataSource.getUsers()
                .map(result -> {
                    if (result.isSuccess()) {
                        List<User> userList = new ArrayList<>();
                        for (UserDto dto : result.getData()) {
                            userList.add(UserMapper.fromDto(dto));
                        }
                        return Result.success(userList);
                    } else {
                        return Result.error(result.getError());
                    }
                });
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
