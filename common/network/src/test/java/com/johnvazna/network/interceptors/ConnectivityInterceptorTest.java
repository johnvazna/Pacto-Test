package com.johnvazna.network.interceptors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import com.johnvazna.network.exceptions.NoConnectivityException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import okhttp3.Interceptor;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityInterceptorTest {

    @Mock
    private Context context;

    @Mock
    private ConnectivityManager connectivityManager;

    @Mock
    private Network network;

    @Mock
    private NetworkCapabilities networkCapabilities;

    @InjectMocks
    private ConnectivityInterceptor connectivityInterceptor;

    @Before
    public void setUp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when(connectivityManager.getActiveNetwork()).thenReturn(network);
            when(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities);
        }
    }

    @Test(expected = NoConnectivityException.class)
    public void intercept_NoConnectivity_ThrowsNoConnectivityException() throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)).thenReturn(false);
            when(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)).thenReturn(false);
            when(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)).thenReturn(false);
        }

        Interceptor.Chain chain = mock(Interceptor.Chain.class);
        connectivityInterceptor.intercept(chain);
    }
}
