package com.johnvazna.data.users.remote;

import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.network.utils.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface UsersRemoteDataSource {

    Single<Result<List<UserDto>>> getUsers();
}
