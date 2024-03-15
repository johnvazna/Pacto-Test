package com.johnvazna.data.users.remote;

import static org.mockito.Mockito.when;

import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.network.utils.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public class GetUsersRemoteDataSourceImplTest {

    @Mock
    private UsersService mockUsersService;

    private UserRemoteDataSourceImpl userRemoteDataSource;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRemoteDataSource = new UserRemoteDataSourceImpl(mockUsersService);
    }

    @Test
    public void getUsers_Success() {

        UserDto user1 = new UserDto();
        user1.setLogin("userTest1");
        user1.setId(1);
        user1.setNodeId("nodeId1");
        user1.setAvatarUrl("https://avatar.url/userTest1");
        user1.setGravatarId("gravatarId1");
        user1.setUrl("https://api.github.com/users/userTest1");
        user1.setHtmlUrl("https://github.com/userTest1");
        user1.setFollowersUrl("https://api.github.com/users/userTest1/followers");
        user1.setGistsUrl("https://api.github.com/users/userTest1/gists");
        user1.setStarredUrl("https://api.github.com/users/userTest1/starred");
        user1.setSubscriptionsUrl("https://api.github.com/users/userTest1/subscriptionsUrl");
        user1.setOrganizationsUrl("https://api.github.com/users/userTest1/organizationsUrl");
        user1.setReposUrl("https://api.github.com/users/userTest1/reposUrl");
        user1.setEventsUrl("https://api.github.com/users/userTest1/eventsUrl");
        user1.setReceivedEventsUrl("https://api.github.com/users/userTest1/receivedEventsUrl");
        user1.setType("https://api.github.com/users/userTest1/type");
        user1.setSiteAdmin(true);


        UserDto user2 = new UserDto();
        user2.setLogin("userTest2");
        user2.setId(2);
        user2.setNodeId("nodeId2");
        user2.setAvatarUrl("https://avatar.url/userTest2");
        user2.setGravatarId("gravatarId2");
        user2.setUrl("https://api.github.com/users/userTest2");
        user2.setHtmlUrl("https://github.com/userTest2");
        user2.setFollowersUrl("https://api.github.com/users/userTest2/followers");
        user2.setGistsUrl("https://api.github.com/users/userTest2/gists");
        user2.setStarredUrl("https://api.github.com/users/userTest2/starred");
        user2.setSubscriptionsUrl("https://api.github.com/users/userTest2/subscriptionsUrl");
        user2.setOrganizationsUrl("https://api.github.com/users/userTest2/organizationsUrl");
        user2.setReposUrl("https://api.github.com/users/userTest2/reposUrl");
        user2.setEventsUrl("https://api.github.com/users/userTest2/eventsUrl");
        user2.setReceivedEventsUrl("https://api.github.com/users/userTest2/receivedEventsUrl");
        user2.setType("https://api.github.com/users/userTest2/type");
        user2.setSiteAdmin(true);

        List<UserDto> mockUserList = new ArrayList<>();
        mockUserList.add(user1);
        mockUserList.add(user2);

        when(mockUsersService.getUsers()).thenReturn(Single.just(mockUserList));

        TestObserver<Result<List<UserDto>>> observer = userRemoteDataSource.getUsers().test();

        observer.assertComplete()
                .assertNoErrors()
                .assertValue(Result::isSuccess)
                .assertValue(result -> result.getData().equals(mockUserList));
    }

    @Test
    public void getUsers_Error() {
        Throwable mockError = new RuntimeException("Test error");

        when(mockUsersService.getUsers()).thenReturn(Single.error(mockError));

        TestObserver<Result<List<UserDto>>> observer = userRemoteDataSource.getUsers().test();

        observer.assertComplete()
                .assertNoErrors()
                .assertValue(result -> result.getError() != null)
                .assertValue(result -> Objects.requireNonNull(result.getError().getMessage()).contains("GENERIC"));
    }

    @Test
    public void getUsers_HttpError() {
        Throwable httpError = new HttpException(Response.error(404, ResponseBody.create(
                "", MediaType.parse("application/json"))));

        when(mockUsersService.getUsers()).thenReturn(Single.error(httpError));

        TestObserver<Result<List<UserDto>>> observer = userRemoteDataSource.getUsers().test();

        observer.assertComplete()
                .assertNoErrors()
                .assertValue(result -> result.getError() != null &&
                        Objects.requireNonNull(result.getError().getMessage()).contains("NOT_FOUND"));
    }

    @Test
    public void getUsers_NetworkError() {
        Throwable networkError = new IOException("Failed to connect");

        when(mockUsersService.getUsers()).thenReturn(Single.error(networkError));

        TestObserver<Result<List<UserDto>>> observer = userRemoteDataSource.getUsers().test();

        observer.assertComplete()
                .assertNoErrors()
                .assertValue(result -> result.getError() != null)
                .assertValue(result -> Objects.requireNonNull(result.getError().getMessage()
                ).contains("NETWORK_ERROR"));
    }
}
