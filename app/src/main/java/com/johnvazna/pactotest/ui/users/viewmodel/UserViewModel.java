package com.johnvazna.pactotest.ui.users.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.domain.users.usecases.GetUsersUseCase;
import com.johnvazna.network.utils.Result;
import com.johnvazna.pactotest.bases.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserViewModel extends BaseViewModel {

    private final GetUsersUseCase getUsersUseCase;
    private final MutableLiveData<Result<List<User>>> usersLiveData = new MutableLiveData<>();

    @Inject
    public UserViewModel(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    public LiveData<Result<List<User>>> getUsersLiveData() {
        return usersLiveData;
    }

    public void fetchUsers() {
        call(getUsersUseCase.execute(), usersLiveData::postValue);
    }
}
