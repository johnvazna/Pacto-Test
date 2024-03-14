package com.johnvazna.pactotest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johnvazna.data.users.remote.UsersRemoteDataSource;
import com.johnvazna.data.users.remote.entities.UserDto;
import com.johnvazna.network.utils.Result;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final UsersRemoteDataSource usersRemoteDataSource;
    private final MutableLiveData<Result<List<UserDto>>> usersLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MainViewModel(UsersRemoteDataSource usersRemoteDataSource) {
        this.usersRemoteDataSource = usersRemoteDataSource;
    }

    public LiveData<Result<List<UserDto>>> getUsersLiveData() {
        return usersLiveData;
    }

    public void fetchUsers() {
        disposables.add(usersRemoteDataSource.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersLiveData::setValue, throwable ->
                        usersLiveData.setValue(Result.error(new Exception(throwable)))));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
