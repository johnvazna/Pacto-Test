package com.johnvazna.domain.users.usecases;

import static org.mockito.Mockito.when;

import com.johnvazna.domain.users.UsersDataSource;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.network.utils.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Single;

public class GetUserByUsernameTest {

    @Mock
    private UsersDataSource usersDataSource;

    private GetUserByUsernameUseCase getUserByUsernameUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserByUsernameUseCase = new GetUserByUsernameUseCase(usersDataSource);
    }

    @Test
    public void execute_returnsUserDetailSuccessfully() {
        String username = "testUser";
        UserDetail expectedUserDetail = new UserDetail(
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
                true,
                "testUser",
                "test",
                "test.com",
                "test",
                null,
                null,
                null,
                "test",
                10,
                10,
                10,
                10,
                "test",
                "test"
        );
        Result<UserDetail> expectedResult = Result.success(expectedUserDetail);

        when(usersDataSource.getUserByUsername(username)).thenReturn(Single.just(expectedResult));

        getUserByUsernameUseCase.execute(username)
                .test()
                .assertNoErrors()
                .assertValue(expectedResult);
    }

    @Test
    public void execute_returnsError() {
        String username = "testUser";
        Throwable expectedError = new Exception("Test error");

        when(usersDataSource.getUserByUsername(username)).thenReturn(Single.error(expectedError));

        getUserByUsernameUseCase.execute(username)
                .test()
                .assertError(expectedError);
    }
}
