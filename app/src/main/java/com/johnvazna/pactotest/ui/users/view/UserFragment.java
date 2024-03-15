package com.johnvazna.pactotest.ui.users.view;

import static com.johnvazna.pactotest.utils.Constants.USER_DETAIL;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.pactotest.R;
import com.johnvazna.pactotest.bases.BaseFragment;
import com.johnvazna.pactotest.ui.users.adapters.UsersAdapter;
import com.johnvazna.pactotest.ui.users.viewmodel.UserViewModel;

public class UserFragment extends BaseFragment<UserViewModel> {

    private RecyclerView rvUsers;

    private SearchView searchView;

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
        searchView = view.findViewById(R.id.searchView);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        setupSearchView();
        setupRecyclerView();
        setupSwipeRefresh();
        observeUser();
        observeUsers();
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter(user -> userViewModel.fetchUserByUsername(user.getLogin()));
        rvUsers.setAdapter(usersAdapter);
    }

    private void setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener(this::onRefresh);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                usersAdapter.filter(newText);
                return true;
            }
        });
    }

    private void observeUsers() {
        userViewModel.getUsersLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result.isSuccess() && !result.getData().isEmpty()) {
                    usersAdapter.setUsers(result.getData());
                    searchView.setVisibility(View.VISIBLE);
                    rvUsers.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);

                } else if (result.isSuccess() && result.getData().isEmpty()) {
                    rvUsers.setVisibility(View.GONE);
                    searchView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);

                } else if (result.isError()) {
                    rvUsers.setVisibility(View.GONE);
                    searchView.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                }

                swipeRefresh.setRefreshing(result.isLoading());
            }
        });

        userViewModel.fetchUsers();
    }

    private void observeUser() {
        userViewModel.getUserLiveData().observe(getViewLifecycleOwner(), result -> {
            if (result != null) {
                if (result.isSuccess()) {
                    goToDetailUser(result.getData());

                } else if (result.isError()) {
                    showErrorDialog();
                }
            }
        });
    }

    private void goToDetailUser(UserDetail userDetail) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(USER_DETAIL, userDetail);
        startActivity(intent);
    }

    private void onRefresh() {
        userViewModel.fetchUsers();
    }
}
