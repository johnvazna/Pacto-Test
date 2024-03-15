package com.johnvazna.pactotest.ui.users.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.network.utils.Result;
import com.johnvazna.pactotest.R;
import com.johnvazna.pactotest.bases.BaseFragment;
import com.johnvazna.pactotest.ui.users.viewmodel.UserViewModel;

import java.util.List;

public class UserFragment extends BaseFragment<UserViewModel> {

    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    protected UserViewModel getViewModel() {
        return userViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel.fetchUsers();
        observeUsersLiveData();
    }

    private void observeUsersLiveData() {
        userViewModel.getUsersLiveData().observe(getViewLifecycleOwner(), this::handleResult);
    }

    private void handleResult(Result<List<User>> result) {
        if (result.isSuccess()) {
            List<User> users = result.getData();
            Toast.makeText(requireActivity(), users.get(0).getLogin(), Toast.LENGTH_SHORT).show();

        } else if (result.isError()) {
            Throwable error = result.getError();
        }
    }
}
