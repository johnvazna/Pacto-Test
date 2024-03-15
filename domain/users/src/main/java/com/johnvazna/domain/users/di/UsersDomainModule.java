package com.johnvazna.domain.users.di;

import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.usecases.GetUserByUsernameUseCase;
import com.johnvazna.domain.users.usecases.GetUsersUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UsersDomainModule {

    @Provides
    public static GetUsersUseCase provideGetUsersUseCase(UsersDataSource usersDataSource) {
        return new GetUsersUseCase(usersDataSource);
    }

    @Provides
    public static GetUserByUsernameUseCase provideGetUserByUsernameUseCase(UsersDataSource usersDataSource) {
        return new GetUserByUsernameUseCase(usersDataSource);
    }
}
