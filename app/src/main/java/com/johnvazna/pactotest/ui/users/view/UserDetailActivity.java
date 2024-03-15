package com.johnvazna.pactotest.ui.users.view;

import static com.johnvazna.pactotest.utils.Constants.USER_DETAIL;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.johnvazna.domain.users.entities.UserDetail;
import com.johnvazna.pactotest.R;

import java.util.Objects;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        setupToolbar();
        extractUserDetailAndPopulate();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back);
        if (upArrow != null) {
            upArrow.setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
        }
    }

    private void extractUserDetailAndPopulate() {
        UserDetail userDetail = (UserDetail) getIntent().getSerializableExtra(USER_DETAIL);
        if (userDetail != null) {
            populateUserDetails(userDetail);
            setupGithubButton(userDetail.getHtmlUrl());
        } else {
            finish();
        }
    }

    private void populateUserDetails(UserDetail userDetail) {
        ((TextView) findViewById(R.id.tvName)).setText(userDetail.name());
        ((TextView) findViewById(R.id.tvCompany)).setText(userDetail.company());
        ((TextView) findViewById(R.id.tvUserName)).setText(userDetail.getLogin());
        ((TextView) findViewById(R.id.tvLocation)).setText(userDetail.location());
        Glide.with(this)
                .load(userDetail.getAvatarUrl())
                .circleCrop()
                .placeholder(R.drawable.codercat)
                .into((ImageView) findViewById(R.id.imgProfile));
    }

    private void setupGithubButton(String url) {
        findViewById(R.id.btnGithub).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
