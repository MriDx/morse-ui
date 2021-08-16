package com.mridx.morse.element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.LinearLayoutCompat
import com.mridx.morse.R
import com.mridx.morse.databinding.SettingsItemBinding
import com.mridx.morse.utils.CommonUtils
import com.mridx.morse.utils.CommonUtils.Companion.setSize
import com.mridx.morse.utils.CommonUtils.Companion.setTint

class SettingsItem : LinearLayoutCompat {

    private val binding = SettingsItemBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingsItem)
        try {
            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.SettingsItem_headerText -> binding.headerText.text =
                        typedArray.getText(attr)
                    R.styleable.SettingsItem_valueText -> binding.valueText.text =
                        typedArray.getText(attr)
                    R.styleable.SettingsItem_icon -> binding.icon.setImageDrawable(
                        AppCompatResources.getDrawable(context, typedArray.getResourceId(attr, 0))
                    )
                    R.styleable.SettingsItem_headerTextSize -> binding.headerText.textSize =
                        CommonUtils.convertPixelsToDp(typedArray.getDimension(attr, 0f), context)
                    R.styleable.SettingsItem_headerTextStyle -> binding.headerText.setTypeface(
                        null,
                        typedArray.getInt(attr, 0)
                    )
                    R.styleable.SettingsItem_valueTextSize -> binding.valueText.textSize =
                        CommonUtils.convertPixelsToDp(typedArray.getDimension(attr, 0f), context)
                    R.styleable.SettingsItem_valueTextStyle -> binding.valueText.setTypeface(
                        null,
                        typedArray.getInt(attr, 0)
                    )
                    R.styleable.SettingsItem_iconSize -> {
                        val size = CommonUtils.convertPixelsToDp(
                            typedArray.getDimensionPixelSize(attr, 0).toFloat(), context
                        ).toInt()
                        binding.icon.setSize(size)
                    }
                    R.styleable.SettingsItem_iconTint -> {
                        binding.icon.setTint(typedArray.getColor(attr, 0))
                    }

                }
            }
        } finally {
            typedArray.recycle()
        }

    }


    fun setHeader(s: String) {
        binding.headerText.text = s
    }

    fun setValue(s: String) {
        binding.valueText.text = s
    }

    fun setIcon(icon: Int) {
        binding.icon.setImageDrawable(AppCompatResources.getDrawable(context, icon))
    }


}