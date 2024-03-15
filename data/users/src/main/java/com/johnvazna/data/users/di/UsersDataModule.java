package com.johnvazna.data.users.di;

import com.johnvazna.data.users.UsersRepository;
import com.johnvazna.data.users.local.UsersLocalDataSource;
import com.johnvazna.data.users.local.UsersLocalDataSourceImpl;
import com.johnvazna.data.users.remote.UserRemoteDataSourceImpl;
import com.johnvazna.data.users.remote.UsersRemoteDataSource;
import com.johnvazna.data.users.remote.UsersService;
import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.local.room.users.UsersDao;
import com.johnvazna.network.conectivity.ConnectivityService;

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
    public static UsersLocalDataSource provideUsersLocalDataSource(UsersDao userDao) {
        return new UsersLocalDataSourceImpl(userDao);
    }

    @Provides
    @Singleton
    public static UsersRemoteDataSource provideUsersRemoteDataSource(UsersService usersService) {
        return new UserRemoteDataSourceImpl(usersService);
    }

    @Provides
    @Singleton
    public static UsersDataSource provideUsersRepository(
            ConnectivityService connectivityService,
            UsersLocalDataSource usersLocalDataSource,
            UsersRemoteDataSource usersRemoteDataSource) {
        return new UsersRepository(connectivityService, usersLocalDataSource, usersRemoteDataSource);
    }
}
