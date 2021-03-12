package com.mridx.design.element

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.core.widget.doAfterTextChanged
import com.mridx.design.R
import com.mridx.design.databinding.CustomOtpFieldBinding
import com.mridx.design.utils.CommonUtils
import com.mridx.design.utils.CommonUtils.Companion.setSize
import kotlin.math.roundToInt

class CustomOTPField : LinearLayoutCompat {

    private val binding = CustomOtpFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private var fieldCount = 5
    private var fieldSize = 50
    private var fieldTextSize = 20f
    private var fieldTextColor = Color.BLACK
    private var fieldHintTextColor = Color.GRAY
    private var otp = ""
    private var hintOtp = "0123456"
    private var fieldBackground = R.drawable.rounded_corner_bg

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

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomOTPField)
        try {

            for (i in 0 until typedArray.indexCount) {
                when (val attr = typedArray.getIndex(i)) {
                    R.styleable.CustomOTPField_fieldCount -> fieldCount =
                        typedArray.getInt(attr, fieldCount)
                    R.styleable.CustomOTPField_fieldSize -> {
                        fieldSize = CommonUtils.convertPixelsToDp(
                            typedArray.getDimensionPixelSize(attr, 0).toFloat(),
                            context
                        ).roundToInt()
                    }
                    R.styleable.CustomOTPField_fieldTextSize -> {
                        fieldTextSize = CommonUtils.convertPixelsToDp(
                            typedArray.getDimension(attr, 0f),
                            context
                        )
                    }
                    R.styleable.CustomOTPField_fieldTextColor -> {
                        fieldTextColor = typedArray.getColor(attr, Color.BLACK)
                    }
                    R.styleable.CustomOTPField_fieldHint -> hintOtp =
                        typedArray.getString(attr).toString()
                    R.styleable.CustomOTPField_fieldHintColor -> fieldHintTextColor =
                        typedArray.getColor(attr, fieldHintTextColor)
                    R.styleable.CustomOTPField_fieldBackground -> fieldBackground =
                        typedArray.getResourceId(attr, fieldBackground)
                }
            }

        } finally {
            typedArray.recycle()
        }

        refreshItems()
    }

    private fun refreshItems() {
        binding.fieldHolder.removeAllViews()
        for (i in 0 until fieldCount) {
            val edittext = AppCompatEditText(context)
            val layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(5, 0, 5, 0)
                edittext.layoutParams = it
            }
            edittext.setSize(fieldSize)
            edittext.textSize = fieldTextSize
            edittext.setTextColor(fieldTextColor)
            edittext.inputType = InputType.TYPE_CLASS_TEXT
            edittext.filters = arrayOf(InputFilter.LengthFilter(1))
            if (fieldCount == i + 1) {
                edittext.imeOptions = EditorInfo.IME_ACTION_GO
            } else {
                edittext.imeOptions = EditorInfo.IME_ACTION_NEXT
            }
            edittext.background = AppCompatResources.getDrawable(context, fieldBackground)
            edittext.gravity = Gravity.CENTER
            //setHint(hintOtp)

            edittext.setHintTextColor(fieldHintTextColor)

            edittext.doAfterTextChanged {
                if (it?.length == 1) {
                    if (binding.root.childCount != i + 1) {
                        //not last item
                        binding.root.getChildAt(i + 1).requestFocus()
                    } else {
                        //last item
                        //submit otp
                        Log.d("kaku", "refreshItems: submit otp")
                    }
                } else if (it?.length == 0) {
                    //most probably backspace
                    if (i == 0) {
                        //first item
                        Log.d("kaku", "refreshItems: first item,, do nothing")
                    } else {
                        binding.root.getChildAt(i - 1).requestFocus()
                    }
                }
            }

            binding.fieldHolder.addView(edittext, layoutParams)

        }

    }

    fun getOTP(): String {
        val tmpOtp = StringBuilder()
        binding.fieldHolder.children.forEach {
            tmpOtp.append((it as AppCompatEditText).text)
        }
        return tmpOtp.toString()
    }

    fun getTotalChild() = binding.fieldHolder.childCount

    fun setHint(hint: String) {
        /*for (i in 0 until binding.root.childCount) {
            (binding.root.getChildAt(i) as AppCompatEditText).hint =
                if (hint.length >= i) hint[i].toString() else ""
        }*/
        binding.fieldHolder.forEachIndexed { index, view ->
            if (hint.length > index) // 6 > 4
                (view as AppCompatEditText).hint = binding.fieldHolder.childCount.toString()
        }
    }

}