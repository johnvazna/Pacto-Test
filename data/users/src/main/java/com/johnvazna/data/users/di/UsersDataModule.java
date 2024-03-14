package com.johnvazna.data.users.di;

import com.johnvazna.data.users.remote.UserRemoteDataSourceImpl;
import com.johnvazna.data.users.remote.UsersRemoteDataSource;
import com.johnvazna.data.users.remote.UsersService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public class UsersDataModule {

    @Provides
    @Singleton
    public static UsersService provideUsersService(Retrofit retrofit) {
        return retrofit.create(UsersService.class);
    }

    @Provides
    @Singleton
    public static UsersRemoteDataSource provideUsersRemoteDataSource(UsersService usersService) {
        return new UserRemoteDataSourceImpl(usersService);
    }
}
