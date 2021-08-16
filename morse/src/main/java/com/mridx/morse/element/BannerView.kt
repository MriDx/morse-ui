package com.mridx.morse.element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.mridx.morse.R
import com.mridx.morse.databinding.BannerHolderViewBinding
import com.mridx.morse.databinding.BannerViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BannerView : LinearLayoutCompat {

    private val binding = BannerHolderViewBinding.inflate(LayoutInflater.from(context), this, true)


    @Deprecated(message = "")
    var bannerCount = 1
        set(value) = run {
            field = value
            bannerAdapter._bannerCount = bannerCount
            post { bannerAdapter.notifyDataSetChanged() }
        }

    var banners: List<String> = listOf()
        set(value) = run {
            field = value
            bannerAdapter.banners = banners
            post {
                setupIndicators()
                bannerAdapter.notifyDataSetChanged()
            }
        }

    var onBannerClicked: ((index: Int) -> Unit)? = null
        set(value) = run {
            field = value
            post {
                bannerAdapter.onBannerClicked = onBannerClicked
            }
        }

    var autoScroll: Boolean = false
        set(value) = run {
            field = value
        }

    var scrollDelay: Int = 2
        set(value) = run {
            field = value
        }

    var lifeCycleScope: LifecycleCoroutineScope? = null
        set(value) = run { field = value }


    private var bannerItems = 0


    private var currentIndex = 0

    private val bannerAdapter by lazy {
        BannerAdapter()
    }

    constructor(context: Context) : super(context) {
        render(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        render(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        render(context, attrs, defStyleAttr)
    }

    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {


        binding.bannerHolder.apply {
            adapter = bannerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setActiveIndicatorAt(position)
                }
            })
        }

        //startAutoScroll()


    }

    fun startAutoScroll() {
        if (!autoScroll) return
        var _next = binding.bannerHolder.currentItem
        if (_next == banners.size - 1) {
            _next = 0
        } else {
            _next += 1
        }
        binding.bannerHolder.currentItem = _next
        if (lifeCycleScope == null)
            throw IllegalArgumentException("lifecycle scope can not be null")
        lifeCycleScope!!.launch {
            delay(2000)
            startAutoScroll()
        }

    }

    private fun setupIndicators() {
        val indicatorParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        indicatorParams.setMargins(4, 0, 4, 0)

        for (i in banners.indices) {
            binding.indicatorHolder.addView(ShapeableImageView(context.applicationContext).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        context.applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                layoutParams = indicatorParams
            }
            )
        }
        setActiveIndicatorAt(0)
    }

    private fun setActiveIndicatorAt(position: Int) {
        for (i in 0 until binding.indicatorHolder.childCount) {
            when (i) {
                position -> {
                    (binding.indicatorHolder.getChildAt(i) as ShapeableImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.indicator_active
                        )
                    )
                }
                else -> {
                    (binding.indicatorHolder.getChildAt(i) as ShapeableImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.indicator_inactive
                        )
                    )
                }
            }
        }

    }


    private class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

        var _bannerCount = 1
            set(value) = run { field = value }


        var banners: List<String> = listOf()
            set(value) = run {
                field = value
            }

        var onBannerClicked: ((index: Int) -> Unit)? = null

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                BannerViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(banners[position], position)
        }

        //override fun getItemCount() = _bannerCount

        override fun getItemCount() = banners.size

        inner class ViewHolder(private val binding: BannerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(s: String, p: Int) {
                Glide.with(binding.root.context).asBitmap().load(s).diskCacheStrategy(
                    DiskCacheStrategy.AUTOMATIC
                ).into(binding.bannerView)
                binding.root.setOnClickListener {
                    onBannerClicked?.invoke(p)
                }
            }

        }

    }

}