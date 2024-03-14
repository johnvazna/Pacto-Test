package com.johnvazna.network;

import android.content.Context;

import com.johnvazna.network.interceptors.ConnectivityInterceptor;
import com.johnvazna.network.interceptors.RetryInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    @Singleton
    public static OkHttpClient provideConfiguredOkHttpClient(
            Cache cache,
            HttpLoggingInterceptor httpLoggingInterceptor,
            ConnectivityInterceptor connectivityInterceptor
    ) {

        RetryInterceptor retryInterceptor = new RetryInterceptor(3, 2000);

        return new OkHttpClient.Builder()
                .addInterceptor(retryInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(connectivityInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    public static ConnectivityInterceptor provideConnectivityInterceptor(
            @ApplicationContext Context context) {
        return new ConnectivityInterceptor(context);
    }


    @Provides
    @Singleton
    public static Cache provideHttpCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }

    @Provides
    @Singleton
    public static File provideCacheFile(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
