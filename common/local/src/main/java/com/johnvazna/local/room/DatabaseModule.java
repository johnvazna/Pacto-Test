package com.johnvazna.local.room;

import android.content.Context;

import androidx.room.Room;

import com.johnvazna.local.room.users.UsersDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "Pacto.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public static UsersDao provideUsersDao(AppDatabase database) {
        return database.usersDao();
    }
}
