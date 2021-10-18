package com.mridx.morse.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.setMargins
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textview.MaterialTextView
import com.mridx.morse.example.databinding.ActivityMainBinding
import com.mridx.morse.example.databinding.ShowCaseActivityBinding

class ShowCaseActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: ShowCaseActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        binding = ShowCaseActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ca.apply {
            categoryCount = 10
            categoryBinding = { index ->
                LinearLayoutCompat(this@ShowCaseActivity).apply {
                    layoutParams = LinearLayoutCompat.LayoutParams(300, 300).apply {
                        setMargins(5)
                    }
                    addView(
                        MaterialTextView(this@ShowCaseActivity).apply {
                            layoutParams = LinearLayoutCompat.LayoutParams(
                                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
                            )
                            text = "This is $index"
                            textSize = 20f
                        }
                    )

                }
            }
        }


    }
}