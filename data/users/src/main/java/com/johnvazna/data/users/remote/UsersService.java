package com.johnvazna.data.users.remote;

import com.johnvazna.data.users.remote.entities.UserDetailDto;
import com.johnvazna.data.users.remote.entities.UserDto;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsersService {

    @GET("users")
    Single<List<UserDto>> getUsers();

    @GET("users/{username}")
    Single<UserDetailDto> getUserByUsername(@Path("username") String username);
}
