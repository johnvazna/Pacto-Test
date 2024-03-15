package com.johnvazna.local.room.users;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.johnvazna.local.room.users.entities.UserEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserEntity> users);

    @Query("SELECT * FROM users")
    Single<List<UserEntity>> getAllUsers();
}
