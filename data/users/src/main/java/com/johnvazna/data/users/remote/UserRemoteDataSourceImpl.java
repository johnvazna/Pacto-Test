package com.johnvazna.data.users.remote;

import com.johnvazna.data.users.remote.entities.UserDetailDto;
import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.network.BaseRemoteDataSource;
import com.johnvazna.network.utils.Result;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UserRemoteDataSourceImpl extends BaseRemoteDataSource implements UsersRemoteDataSource {

    private final UsersService usersService;

    @Inject
    public UserRemoteDataSourceImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Single<Result<List<UserDto>>> getUsers() {
        return getResult(usersService.getUsers());
    }

    @Override
    public Single<Result<UserDetailDto>> getUserByUsername(String name) {
        return getResult(usersService.getUserByUsername(name));
    }
}
