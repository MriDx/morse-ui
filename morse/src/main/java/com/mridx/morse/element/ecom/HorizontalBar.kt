package com.mridx.morse.element.ecom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import com.mridx.morse.R
import com.mridx.morse.databinding.HorizontalBarBinding

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

    var title = ""
        set(value) = run {
            field = value
            binding.title.text = field
        }


    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalBar)
        try {

            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.HorizontalBar_title -> {
                        binding.title.text = typedArray.getString(attr)
                        //title = typedArray.getString(attr).toString()
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