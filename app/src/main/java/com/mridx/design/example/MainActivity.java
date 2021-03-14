package com.mridx.design.example;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mridx.design.element.CustomOTPField;
import com.mridx.design.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*new Handler().postDelayed(() -> {
            binding.progressButton.showProgressbar(true);
            new Handler().postDelayed(() -> binding.progressButton.showProgressbar(false), 1000 * 2);
        }, 1000 * 2);*/

        binding.progressButton.setClickListener(view -> {
            binding.progressButton.showProgressbar(true);
            new Handler().postDelayed(() -> binding.progressButton.showProgressbar(false), 1000 * 2);
        });


    }
}
