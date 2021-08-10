package com.mridx.design.element.ecom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    var buildItem: ((parent: ViewGroup, index: Int) -> View)? = null


    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryView)
        try {
            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.CategoryView_title -> {
                        binding.horizontalBar.title = typedArray.getString(attr).toString()
                    }
                }
            }

        } finally {
            typedArray.recycle()
        }



        binding.itemHolder.apply {
            setItemCount(itemCount)
            itemBuilder = { parent, index ->
                buildItem?.invoke(parent, index)
            }
            layoutManager = GridLayoutManager(context, 2)
        }.render()

    }

    var itemCount = 4
        set(value) = run {
            field = value
            binding.itemHolder.apply {
                setItemCount(value)
            }.render()
        }

}