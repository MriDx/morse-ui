package com.mridx.design.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mridx.design.example.databinding.ActivityMainBinding

class ShowCaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bannerView.apply {
            banners = listOf(
                "https://static.vecteezy.com/system/resources/thumbnails/002/006/614/small/paper-art-shopping-online-on-smartphone-and-new-buy-sale-promotion-pink-backgroud-for-banner-market-ecommerce-free-vector.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSFthyVWAsRSLAS1B2IgiD_TKkeNnRQwsDcg&usqp=CAU",
                "https://www.krativyas.com/img/product_banner/3333132190971570457519.jpg"
            )
            lifeCycleScope = lifecycleScope
            autoScroll = true
        }.startAutoScroll()


        binding.categoryView.apply {
            itemCount = 6
        }


    }
}