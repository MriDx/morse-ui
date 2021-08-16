package com.mridx.morse.element

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.mridx.morse.databinding.SearchBarBinding

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

    var hint = "Search here"
        set(value) = run {
            field = value
            binding.field.hint = value
        }

    private fun render(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        hint = "Search here"

        binding.icon.setOnClickListener {
            onSearchAction?.invoke(binding.field.text.toString())
        }


        binding.field.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN
                && i == KeyEvent.KEYCODE_ENTER
            ) {
                onSearchAction?.invoke(binding.field.text.toString())
            }
            return@setOnKeyListener true
        }

    }

}