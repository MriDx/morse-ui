package com.mridx.design.element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.mridx.design.databinding.SearchBarBinding

class SearchBar : LinearLayoutCompat {


    private val binding = SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    var onSearchAction: ((query: String) -> Unit)? = null

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

        binding.icon.setOnClickListener {
            onSearchAction?.invoke(binding.field.text.toString())
        }

    }

}