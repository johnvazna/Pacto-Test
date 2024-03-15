package com.johnvazna.domain.users.usecases;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.network.utils.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class GetUsersUseCaseTest {

    @Mock
    private UsersDataSource usersDataSource;

    private GetUsersUseCase getUsersUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getUsersUseCase = new GetUsersUseCase(usersDataSource);
    }

    @Test
    public void getUsers_ReturnsSuccess() {
        List<User> mockUsers = Arrays.asList(
                new User(
                        "userTest1",
                        1,
                        "nodeId1",
                        "https://avatar.url/userTest1",
                        "gravatarId1",
                        "https://api.github.com/users/userTest1",
                        "https://github.com/userTest1",
                        "https://api.github.com/users/userTest1/followers",
                        "https://api.github.com/users/userTest1/gists",
                        "https://api.github.com/users/userTest1/starred",
                        "https://api.github.com/users/userTest1/subscriptionsUrl",
                        "https://api.github.com/users/userTest1/organizationsUrl",
                        "https://api.github.com/users/userTest1/reposUrl",
                        "https://api.github.com/users/userTest1/eventsUrl",
                        "https://api.github.com/users/userTest1/receivedEventsUrl",
                        "https://api.github.com/users/userTest1/type",
                        true
                ),
                new User(
                        "userTest2",
                        1,
                        "nodeId2",
                        "https://avatar.url/userTest2",
                        "gravatarId2",
                        "https://api.github.com/users/userTest2",
                        "https://github.com/userTest2",
                        "https://api.github.com/users/userTest2/followers",
                        "https://api.github.com/users/userTest2/gists",
                        "https://api.github.com/users/userTest2/starred",
                        "https://api.github.com/users/userTest2/subscriptionsUrl",
                        "https://api.github.com/users/userTest2/organizationsUrl",
                        "https://api.github.com/users/userTest2/reposUrl",
                        "https://api.github.com/users/userTest2/eventsUrl",
                        "https://api.github.com/users/userTest2/receivedEventsUrl",
                        "https://api.github.com/users/userTest2/type",
                        false
                )
        );

        when(usersDataSource.getUsers()).thenReturn(Single.just(Result.success(mockUsers)));

        getUsersUseCase = new GetUsersUseCase(usersDataSource);

        getUsersUseCase.execute()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(result -> result.getData().equals(mockUsers));

        verify(usersDataSource).getUsers();
    }
}
