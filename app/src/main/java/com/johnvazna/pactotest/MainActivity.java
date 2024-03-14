package com.johnvazna.pactotest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getUsersLiveData().observe(this, result -> {
            if (result != null && result.isSuccess()) {
                Log.i(MainActivity.class.getSimpleName(), result.getData().toString());

            } else if (result != null) {
                Log.i(MainActivity.class.getSimpleName(), result.getError().toString());
            }
        });

        mainViewModel.fetchUsers();
    }
}
