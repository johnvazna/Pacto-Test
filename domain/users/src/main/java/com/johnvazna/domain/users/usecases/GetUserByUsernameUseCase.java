package com.johnvazna.domain.users.usecases;

import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.network.utils.Result;

import io.reactivex.rxjava3.core.Single;

public class GetUserByUsernameUseCase {

    private final UsersDataSource usersDataSource;

    public GetUserByUsernameUseCase(UsersDataSource usersDataSource) {
        this.usersDataSource = usersDataSource;
    }

    public Single<Result<UserDetail>> execute(
            String name
    ) {
        return usersDataSource.getUserByUsername(name);
    }
}
