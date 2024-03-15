package com.johnvazna.pactotest.bases;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johnvazna.network.RxSchedulers;
import com.johnvazna.network.utils.Result;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;

public class BaseViewModel extends ViewModel {

    private final MutableLiveData<Result<?>> _state = new MutableLiveData<>();
    public LiveData<Result<?>> state = _state;

    protected CompositeDisposable disposables = new CompositeDisposable();

    protected void setLoading() {
        _state.postValue(Result.loading());
    }

    protected <T> Result<T> setSuccess(T data) {
        return Result.success(data);
    }

    protected void setError(Throwable error) {
        _state.postValue(Result.error(error));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    protected <T> void call(Single<T> single, Consumer<T> onSuccess) {
        setLoading();

        disposables.add(single
                .compose(RxSchedulers.applySingleSchedulers())
                .subscribe(
                        data -> {
                            setSuccess(data);
                            onSuccess.accept(data);
                            _state.postValue(Result.success(data));
                        },
                        error -> {
                            setError(error);
                            _state.postValue(Result.error(error));
                        }
                )
        );
    }
}
