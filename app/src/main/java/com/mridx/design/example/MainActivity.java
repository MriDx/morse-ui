package com.mridx.design.example;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mridx.design.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Log.d("kaku", "onCreate: " + binding.customOTP.getTotalChild());

        Log.d("kaku", "onCreate: " + binding.customOTP.getOTP());


        binding.getOTP.setOnClickListener(view -> {
            Log.d("kaku", "onCreate: " + binding.customOTP.getOTP());
        });



    }
}
