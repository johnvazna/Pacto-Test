package com.johnvazna.network;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxSchedulers {

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static CompletableTransformer applyCompletableSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
