package com.johnvazna.network.utils;

public class Result<T> {
    public enum Status {LOADING, SUCCESS, ERROR}

    private final Status status;
    private final T data;
    private final Throwable error;

    private Result(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Result<T> loading() {
        return new Result<>(Status.LOADING, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Status.SUCCESS, data, null);
    }

    public static <T> Result<T> error(Throwable error) {
        return new Result<>(Status.ERROR, null, error);
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isLoading() {
        return status == Status.LOADING;
    }

    public boolean isError() {
        return status == Status.ERROR;
    }
}
