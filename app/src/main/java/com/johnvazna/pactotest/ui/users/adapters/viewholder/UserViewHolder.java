package com.johnvazna.pactotest.ui.users.adapters.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.johnvazna.domain.users.entities.User;
import com.johnvazna.pactotest.R;

import io.reactivex.rxjava3.functions.Consumer;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvUserName;
    private final ImageView ivUserAvatar;
    private User user;

    public UserViewHolder(View itemView, Consumer<User> onUserClicked) {
        super(itemView);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        ivUserAvatar = itemView.findViewById(R.id.ivUserAvatar);

        itemView.setOnClickListener(v -> {
            try {
                onUserClicked.accept(user);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void bind(User user, Context context) {
        this.user = user;
        tvUserName.setText(user.getLogin());

        Glide.with(context)
                .load(user.getAvatarUrl())
                .circleCrop()
                .placeholder(R.drawable.codercat)
                .into(ivUserAvatar);
    }
}
