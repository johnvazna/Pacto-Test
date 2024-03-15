package com.johnvazna.network.interceptors;

import androidx.annotation.NonNull;

import com.johnvazna.network.conectivity.ConnectivityService;
import com.johnvazna.network.exceptions.NoConnectivityException;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private final ConnectivityService connectivityService;

    @Inject
    public ConnectivityInterceptor(ConnectivityService connectivityService) {
        this.connectivityService = connectivityService;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!connectivityService.isOnline()) {
            throw new NoConnectivityException();
        }
        return chain.proceed(chain.request());
    }
}
