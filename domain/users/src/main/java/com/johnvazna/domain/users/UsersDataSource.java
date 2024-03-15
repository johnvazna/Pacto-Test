package com.johnvazna.domain.users;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.network.utils.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface UsersDataSource {

    Single<Result<List<User>>> getUsers();

    Single<Result<UserDetail>> getUserByUsername(String name);
}
