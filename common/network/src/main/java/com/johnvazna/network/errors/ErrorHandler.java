package com.johnvazna.network.errors;

public interface ErrorHandler {
    ErrorType getError(Throwable throwable);
}
