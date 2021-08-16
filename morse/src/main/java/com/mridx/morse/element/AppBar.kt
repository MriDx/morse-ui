package com.mridx.morse.element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.mridx.morse.R
import com.mridx.morse.databinding.AppBarBinding

class AppBar : ConstraintLayout {

    companion object {
        const val MENU = 0
        const val BACK = 1
    }

    private var appBarType = MENU

    private var showHome = true
    private var showCart = true


    private val binding = AppBarBinding.inflate(LayoutInflater.from(context), this, true)


    var appBarListener: ((id: Int) -> Unit)? = null

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppBar)
        try {
            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.AppBar_title -> {
                        binding.titleView.text = typedArray.getString(attr)
                    }
                    R.styleable.AppBar_headerIcon -> {
                        binding.rightIcon.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context, typedArray.getResourceId(
                                    attr,
                                    0
                                )
                            )
                        )
                    }
                    R.styleable.AppBar_cartIcon -> {
                        binding.cartIcon.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context, typedArray.getResourceId(
                                    attr,
                                    0
                                )
                            )
                        )
                    }
                    R.styleable.AppBar_homeIcon -> {
                        binding.homeIcon.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context, typedArray.getResourceId(
                                    attr,
                                    0
                                )
                            )
                        )
                    }
                    R.styleable.AppBar_showHome -> {
                        showHome = typedArray.getBoolean(attr, showHome)
                    }
                    R.styleable.AppBar_showCart -> {
                        showCart = typedArray.getBoolean(attr, showCart)
                    }
                    R.styleable.AppBar_appBarType -> {
                        appBarType = typedArray.getInt(attr, MENU)
                        menuIcon(appBarType)
                    }
                }
            }


        } finally {
            typedArray.recycle()
        }

        binding.homeIcon.isVisible = showHome
        binding.cartIcon.isVisible = showCart

        binding.rightIcon.setOnClickListener {
            appBarListener?.invoke(0)
        }
        binding.cartIcon.setOnClickListener {
            appBarListener?.invoke(1)
        }
        binding.homeIcon.setOnClickListener {
            appBarListener?.invoke(2)
        }

    }

    fun setTitle(show: Boolean, title: String) {
        binding.titleView.apply {
            text = title
        }.isVisible = show
    }

    fun showTitle(b: Boolean) {
        binding.titleView.isVisible = b
    }

    fun showHome(b: Boolean) {
        showHome = b
        binding.homeIcon.isVisible = showHome
    }

    fun showCart(b: Boolean) {
        showCart = b
        binding.cartIcon.isVisible = showCart
    }

    fun menuIcon(type: Int) {
        when (type) {
            BACK -> {
                //binding.rightIcon.setImageResource(R.drawable.ic_back)
            }
            else -> {
                //binding.rightIcon.setImageResource(R.drawable.ic_menu)
            }
        }
    }


}