package com.johnvazna.data.users.remote;

import static org.mockito.Mockito.when;

import com.johnvazna.data.users.remote.entities.UserDetailDto;
import com.johnvazna.network.utils.Result;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Objects;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public class GetUserRemoteDataSourceImplTest {

    @Mock
    private UsersService mockUsersService;

    private UserRemoteDataSourceImpl userRemoteDataSource;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userRemoteDataSource = new UserRemoteDataSourceImpl(mockUsersService);
    }

    @Test
    public void getUserByUsername_Success() {
        UserDetailDto mockUserDetail = new UserDetailDto();
        mockUserDetail.setName("Test User");

        when(mockUsersService.getUserByUsername("TestUser")).thenReturn(Single.just(mockUserDetail));

        TestObserver<Result<UserDetailDto>> testObserver =
                userRemoteDataSource.getUserByUsername("TestUser").test();

        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Result::isSuccess)
                .assertValue(result -> result.getData().name().equals("Test User"));
    }

    @Test
    public void getUserByUsername_Error() {
        when(mockUsersService.getUserByUsername("NonExistentUser"))
                .thenReturn(Single.error(new HttpException(Response.error(404,
                        ResponseBody.create("", MediaType.parse("application/json"))))));

        TestObserver<Result<UserDetailDto>> testObserver = userRemoteDataSource.getUserByUsername("NonExistentUser").test();

        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(result -> result.getError() != null)
                .assertValue(result -> Objects.requireNonNull(
                        result.getError().getMessage()).contains("NOT_FOUND"));
    }
}
