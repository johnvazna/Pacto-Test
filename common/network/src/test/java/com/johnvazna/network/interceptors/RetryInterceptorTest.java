package com.johnvazna.network.interceptors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class RetryInterceptorTest {

    @Mock
    private Interceptor.Chain chain;

    @Mock
    private Call call;

    private Request request;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new Request.Builder().url("https://api.github.com").build();

        when(chain.request()).thenReturn(request);
        when(chain.call()).thenReturn(call);
    }

    @Test
    public void interceptorRetriesOnFailure() throws IOException {
        // Simulates an IOException on the first 2 attempts and success on the third
        when(chain.proceed(request))
                .thenThrow(new IOException("Simulated network failure"))
                .thenThrow(new IOException("Simulated network failure"))
                .thenReturn(new Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("OK")
                        .build());

        RetryInterceptor interceptor = new RetryInterceptor(3, 1000);
        Response response = interceptor.intercept(chain);

        assertNotNull(response);
        assertEquals(200, response.code());


        verify(chain, times(3)).proceed(request);
    }
}
