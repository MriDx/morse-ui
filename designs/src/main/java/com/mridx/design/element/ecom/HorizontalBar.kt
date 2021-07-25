package com.mridx.design.element.ecom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.mridx.design.R
import com.mridx.design.databinding.HorizontalBarBinding

class HorizontalBar : LinearLayoutCompat {


    private val binding = HorizontalBarBinding.inflate(LayoutInflater.from(context), this, true)

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalBar)
        try {

            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.HorizontalBar_title -> {
                        binding.title.text = typedArray.getString(attr)
                    }
                    R.styleable.HorizontalBar_android_textColor -> {
                        binding.title.setTextColor(typedArray.getColor(attr, 0))
                    }
                    R.styleable.HorizontalBar_icon -> {
                        binding.icon.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context,
                                typedArray.getResourceId(attr, 0)
                            )
                        )
                    }
                }
            }

        } finally {
            typedArray.recycle()
        }

    }
}