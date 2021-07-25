package com.mridx.design.element.ecom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.mridx.design.R
import com.mridx.design.databinding.CategoryViewBoxBinding
import com.mridx.design.databinding.ItemViewFullWBinding

class CategoryView : LinearLayoutCompat {

    private val binding = CategoryViewBoxBinding.inflate(LayoutInflater.from(context), this, true)


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


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryView)

        try {


        } finally {
            typedArray.recycle()
        }

        binding.itemHolder.apply {
            setItemCount(4)
            itemBuilder = { parent, index ->
                ItemViewFullWBinding.inflate(LayoutInflater.from(context), parent, false).apply {

                }.root
            }
            layoutManager = GridLayoutManager(context, 2)
        }.render()


    }
}