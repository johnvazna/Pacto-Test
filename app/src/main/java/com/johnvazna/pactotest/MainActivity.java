package com.johnvazna.pactotest;

import android.os.Bundle;

import com.johnvazna.pactotest.bases.BaseActivity;
import com.johnvazna.pactotest.ui.users.view.UserFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, UserFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        }
    }
}
