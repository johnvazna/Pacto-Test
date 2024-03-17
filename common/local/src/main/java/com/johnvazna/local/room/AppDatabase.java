package com.johnvazna.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.johnvazna.local.room.users.UsersDao;
import com.johnvazna.local.room.users.entities.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UsersDao usersDao();
}
