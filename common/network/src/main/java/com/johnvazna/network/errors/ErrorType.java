package com.johnvazna.network.errors;

public enum ErrorType {
    UNAUTHORIZED, // Represents a 401 error
    FORBIDDEN, // Represents a 403 error
    NOT_FOUND, // Represents a 404 error
    SERVER_ERROR, // Represents a 500 error
    TIMEOUT, // Specific for network timeouts
    NETWORK_ERROR, // For other network errors (IOException)
    GENERIC // For any other type of error not specified
}
