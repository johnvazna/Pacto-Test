package com.johnvazna.network;

import com.johnvazna.network.errors.ErrorHandler;
import com.johnvazna.network.errors.ErrorType;
import com.johnvazna.network.utils.Result;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.rxjava3.core.Single;
import retrofit2.HttpException;

public class BaseRemoteDataSource implements ErrorHandler {

    protected <T> Single<Result<T>> getResult(Single<T> call) {
        return call
                .map(Result::success)
                .onErrorReturn(throwable -> Result.error(new Exception(getError(throwable).name())));
    }

    @Override
    public ErrorType getError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 401: // Unauthorized
                    return ErrorType.UNAUTHORIZED;
                case 403: // Forbidden
                    return ErrorType.FORBIDDEN;
                case 404: // Not Found
                    return ErrorType.NOT_FOUND;
                case 500: // Internal Server Error
                    return ErrorType.SERVER_ERROR;
                default:
                    return ErrorType.GENERIC;
            }
        } else if (throwable instanceof IOException) {
            if (throwable instanceof SocketTimeoutException) {
                return ErrorType.TIMEOUT;
            } else {
                return ErrorType.NETWORK_ERROR;
            }
        }
        return ErrorType.GENERIC;
    }
}
