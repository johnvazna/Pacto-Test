package com.johnvazna.network.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class RetryInterceptor implements Interceptor {

    private final int maxRetries;

    private final int retryDelayMillis;

    public RetryInterceptor(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        IOException exception = null;

        for (int i = 0; i < maxRetries; i++) {
            try {
                Response response = chain.proceed(chain.request());
                if (response.isSuccessful()) {
                    return response;
                }

            } catch (IOException e) {
                exception = e;

                try {
                    Thread.sleep(retryDelayMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry", ie);
                }
            }
        }

        throw exception != null ? exception : new IOException("Unknown network error");
    }
}
