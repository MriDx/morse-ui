package com.mridx.morse.element.ecom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mridx.morse.databinding.ExpandedItemsByCategoryBinding

class ExpandedCategoryView : LinearLayoutCompat {

    private val binding =
        ExpandedItemsByCategoryBinding.inflate(LayoutInflater.from(context), this, true)

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

    var categoryCount = 0
        set(value) = run {
            field = value
            binding.categoryHolder.setItemCount(value)
        }

    var categoryBinding: ((index: Int) -> View)? = null
        set(value) = run {
            field = value
        }

    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        binding.categoryHolder.apply {
            itemBuilder = { _, _ ->
                LinearLayoutCompat(context)
            }
            itemBinding { holder, index ->
                (holder.itemView as LinearLayoutCompat)
                    .addView(categoryBinding?.invoke(index))
            }
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }.render()

        

    }
}