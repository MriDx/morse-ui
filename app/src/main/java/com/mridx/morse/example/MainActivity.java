package com.mridx.morse.example;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mridx.morse.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /* new Handler().postDelayed(() -> {
            binding.progressButton.showProgressbar(true);
            new Handler().postDelayed(() -> binding.progressButton.showProgressbar(false), 1000 * 2);
        }, 1000 * 2);*/

       /* binding.phoneLoginBtn.setClickListener(view -> {
            binding.phoneLoginBtn.showProgressbar(true);
            new Handler().postDelayed(() -> binding.phoneLoginBtn.showProgressbar(false), 1000 * 5);
        });*/



    }

}
