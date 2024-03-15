package com.johnvazna.pactotest.ui.users.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johnvazna.domain.users.entities.User;
import com.johnvazna.pactotest.R;
import com.johnvazna.pactotest.ui.users.adapters.viewholder.UserViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> users = new ArrayList<>();
    private List<User> usersFull;

    private final Consumer<User> onUserClicked;

    public UsersAdapter(Consumer<User> onUserClicked) {
        this.onUserClicked = onUserClicked;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView, onUserClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(users.get(position), holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        this.usersFull = new ArrayList<>(users);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        users.clear();
        if (text.isEmpty()) {
            users.addAll(usersFull);
        } else {
            text = text.toLowerCase();
            for (User user : usersFull) {
                if (user.getLogin().toLowerCase().contains(text)) {
                    users.add(user);
                }
            }
        }
        notifyDataSetChanged();
    }
}
