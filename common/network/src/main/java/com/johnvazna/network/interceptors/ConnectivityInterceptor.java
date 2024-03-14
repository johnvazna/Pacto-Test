package com.johnvazna.network.interceptors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;

import com.johnvazna.network.exceptions.NoConnectivityException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {

    private final Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (context == null) {
            throw new NoConnectivityException();
        }

        if (!isOnline()) {
            throw new NoConnectivityException();
        }
        return chain.proceed(chain.request());
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // For Android 6.0 (API level 23) and later
                Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    return networkCapabilities != null &&
                            (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
                }
            }

        } else {
            // Code for versions prior to Android 6.0 (API level 23)
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }

        return false;
    }
}
