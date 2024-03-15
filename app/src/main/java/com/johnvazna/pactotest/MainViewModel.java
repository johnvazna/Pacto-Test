package com.johnvazna.pactotest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.usecases.GetUsersUseCase;
import com.johnvazna.network.utils.Result;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final GetUsersUseCase getUsersUseCase;
    private final MutableLiveData<Result<List<User>>> usersLiveData = new MutableLiveData<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MainViewModel(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    public LiveData<Result<List<User>>> getUsersLiveData() {
        return usersLiveData;
    }

    public void fetchUsers() {
        disposables.add(getUsersUseCase.execute()
                .subscribe(usersLiveData::setValue, throwable ->
                        usersLiveData.setValue(Result.error(new Exception(throwable)))));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
