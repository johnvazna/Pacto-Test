package com.johnvazna.domain.users.usecases;

import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.network.utils.Result;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class GetUsersUseCase {

    private final UsersDataSource usersDataSource;

    public GetUsersUseCase(UsersDataSource usersDataSource) {
        this.usersDataSource = usersDataSource;
    }

    public Single<Result<List<User>>> execute() {
        return usersDataSource.getUsers();
    }
}
