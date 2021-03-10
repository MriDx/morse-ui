package com.mridx.design.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mridx.design.example.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.phoneField.apply {
            this.setFieldType(FIELD_TYPE_ADDRESS)
        }


    }
}