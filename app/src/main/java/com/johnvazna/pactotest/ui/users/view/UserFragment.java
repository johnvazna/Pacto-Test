package com.johnvazna.pactotest.ui.users.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.johnvazna.pactotest.R;
import com.johnvazna.pactotest.bases.BaseFragment;
import com.johnvazna.pactotest.ui.users.adapters.UsersAdapter;
import com.johnvazna.pactotest.ui.users.viewmodel.UserViewModel;

public class UserFragment extends BaseFragment<UserViewModel> {

    private RecyclerView rvUsers;

    private LinearLayout emptyView;

    private UsersAdapter usersAdapter;

    private UserViewModel userViewModel;

    private SwipeRefreshLayout swipeRefresh;

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
        rvUsers = view.findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        emptyView = view.findViewById(R.id.emptyView);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        setupRecyclerView();
        setupSwipeRefresh();
        observeUsers();
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter(user -> {
            Toast.makeText(getContext(), user.getLogin(), Toast.LENGTH_SHORT).show();
        });

        rvUsers.setAdapter(usersAdapter);
    }

    private void setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void observeUsers() {
        userViewModel.getUsersLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result.isSuccess() && !result.getData().isEmpty()) {
                    usersAdapter.setUsers(result.getData());
                    rvUsers.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);

                } else if (result.isSuccess() && result.getData().isEmpty()) {
                    rvUsers.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);

                } else if (result.isError()) {
                    rvUsers.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }

                swipeRefresh.setRefreshing(result.isLoading());
            }
        });

        userViewModel.fetchUsers();
    }

    private void onRefresh() {
        userViewModel.fetchUsers();
    }
}
