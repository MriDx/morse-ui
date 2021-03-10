package com.mridx.design.element

import android.content.Context
import android.graphics.Typeface
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import com.mridx.design.R
import com.mridx.design.databinding.CustomInputFieldBinding
import com.mridx.design.utils.CommonUtils
import kotlin.math.roundToInt

class CustomInputField : LinearLayoutCompat {

    private val binding = CustomInputFieldBinding.inflate(LayoutInflater.from(context), this, true)


    val FIELD_TYPE_NORMAL = 0
    val FIELD_TYPE_PHONE = 1
    val FIELD_TYPE_PERSON_NAME = 2
    val FIELD_TYPE_ADDRESS = 3
    val FIELD_TYPE_PINCODE = 4
    val FIELD_TYPE_OTP = 5


    var value: String
        get() = binding.field.text.toString()
        set(value) = binding.field.setText(value)

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomInputField)
        try {
            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.CustomInputField_fieldLabel -> binding.fieldLabel.text =
                        typedArray.getString(attr)
                    R.styleable.CustomInputField_fieldLabelSize -> {
                        CommonUtils.convertPixelsToDp(typedArray.getDimension(attr, 0f), context)
                            .also {
                                binding.fieldLabel.textSize = it
                            }
                    }
                    R.styleable.CustomInputField_prefixEnabled -> {
                        if (typedArray.getBoolean(attr, true))
                            binding.prefixLabel.visibility = View.VISIBLE
                        else
                            binding.prefixLabel.visibility = View.GONE
                    }
                    R.styleable.CustomInputField_prefixText -> binding.prefixLabel.text =
                        typedArray.getString(attr)
                    R.styleable.CustomInputField_fieldHint -> binding.field.hint =
                        typedArray.getString(attr)
                    R.styleable.CustomInputField_fieldTextColor -> {
                        typedArray.getColor(attr, 0).also {
                            binding.field.setTextColor(it)
                            binding.prefixLabel.setTextColor(it)
                        }
                    }
                    R.styleable.CustomInputField_fieldTextSize -> {
                        CommonUtils.convertPixelsToDp(typedArray.getDimension(attr, 0f), context)
                            .also {
                                binding.prefixLabel.textSize = it
                                binding.field.textSize = it
                            }
                    }
                    R.styleable.CustomInputField_fieldTextStyle -> {
                        typedArray.getInt(attr, 0).also {
                            binding.field.setTypeface(null, it)
                            binding.prefixLabel.setTypeface(null, it)
                        }
                    }
                    R.styleable.CustomInputField_fieldTopMargin -> {
                        (binding.field.layoutParams as MarginLayoutParams).topMargin =
                            CommonUtils.convertPixelsToDp(
                                typedArray.getDimension(attr, 0f),
                                context
                            ).roundToInt()
                    }
                    R.styleable.CustomInputField_fieldType -> {
                        _setFieldType(typedArray.getInt(attr, 0))
                    }

                }
            }
        } finally {
            typedArray.recycle()
        }

    }

    fun setFieldType(type: Int) = _setFieldType(type)

    private fun _setFieldType(type: Int) {
        when (type) {
            FIELD_TYPE_PHONE -> {
                binding.field.apply {
                    this.filters = arrayOf(InputFilter.LengthFilter(10))
                    this.inputType =
                        InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_SIGNED + InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE + InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                }
            }
            FIELD_TYPE_PERSON_NAME -> {
                binding.field.apply {
                    this.inputType =
                        InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE + InputType.TYPE_TEXT_FLAG_AUTO_CORRECT + InputType.TYPE_TEXT_FLAG_CAP_WORDS
                }
            }
            FIELD_TYPE_ADDRESS -> {
                binding.prefixLabel.visibility = View.GONE
                binding.field.apply {
                    this.inputType =
                        InputType.TYPE_CLASS_TEXT + InputType.TYPE_TEXT_FLAG_MULTI_LINE + InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE + InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE + InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                }
            }
            FIELD_TYPE_PINCODE -> {
                binding.prefixLabel.visibility = View.GONE
                binding.field.apply {
                    this.filters = arrayOf(InputFilter.LengthFilter(6))
                    this.inputType =
                        InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_SIGNED + InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE + InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                }
            }
            FIELD_TYPE_OTP -> {

            }
            else -> {

            }
        }
    }


}