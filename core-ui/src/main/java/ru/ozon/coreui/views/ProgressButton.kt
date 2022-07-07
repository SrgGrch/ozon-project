package ru.ozon.coreui.views

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.srggrch.coreui.R
import com.srggrch.coreui.databinding.MergeProgressButtonBinding

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = MergeProgressButtonBinding.inflate(LayoutInflater.from(context), this)

    private var lastText: CharSequence? = null

    var inProgress: Boolean
        get() {
            return binding.progress.isVisible
        }
        set(value) {
            binding.progress.isVisible = value
            if (value) {
                lastText = binding.addToCart.text?.toString()
                binding.addToCart.text = ""
            } else {
                binding.addToCart.text = lastText
            }
        }

    val button: MaterialButton
        get() = binding.addToCart

    var text: CharSequence
        get() = binding.addToCart.text
        set(value) {
            binding.addToCart.text = value
            lastText = value
        }

    init {
        val a: TypedArray = getContext().obtainStyledAttributes(
            attrs,
            R.styleable.ProgressButton
        )

        if (a.getType(R.styleable.ProgressButton_pb_backgroundTint) == TypedValue.TYPE_REFERENCE) {
            a.getResourceId(R.styleable.ProgressButton_pb_backgroundTint, -1).let {
                if (it != -1) {
                    binding.addToCart.backgroundTintList = ContextCompat.getColorStateList(
                        context,
                        it
                    )
                }
            }
        } else {
            a.getColor(R.styleable.ProgressButton_pb_backgroundTint, -1).let {
                if (it != -1) {
                    binding.addToCart.backgroundTintList = ColorStateList.valueOf(it)
                }
            }
        }

        if (a.getType(R.styleable.ProgressButton_pb_textColor) == TypedValue.TYPE_REFERENCE) {
            a.getResourceId(R.styleable.ProgressButton_pb_textColor, -1).let {
                if (it != -1) {
                    binding.addToCart.setTextColor(
                        ContextCompat.getColor(
                            context,
                            it
                        )
                    )
                }
            }
        } else {
            a.getColor(R.styleable.ProgressButton_pb_textColor, -1).let {
                if (it != -1) {
                    binding.addToCart.setTextColor(it)
                }
            }
        }

        if (a.getType(R.styleable.ProgressButton_pb_textSize) == TypedValue.TYPE_REFERENCE) {
            a.getResourceId(R.styleable.ProgressButton_pb_textSize, -1).let {
                if (it != -1) {
                    binding.addToCart.textSize = resources.getDimension(it)
                }
            }
        } else {
            a.getDimensionPixelSize(R.styleable.ProgressButton_pb_textSize, -1).let {
                if (it != -1) {
                    binding.addToCart.setTextSize(TypedValue.COMPLEX_UNIT_PX, it.toFloat())
                }
            }
        }

        if (a.getType(R.styleable.ProgressButton_text) == TypedValue.TYPE_REFERENCE) {
            a.getResourceId(R.styleable.ProgressButton_text, -1).let {
                if (it != -1) {
                    binding.addToCart.setText(it)
                    lastText = resources.getText(it)
                }
            }
        } else {
            a.getText(R.styleable.ProgressButton_text)?.let {
                binding.addToCart.text = it
                lastText = it
            }
        }

        a.recycle()
    }

    fun setText(@StringRes textId: Int) {
        text = resources.getText(textId)
    }

    fun setOnClickListener(block: () -> Unit) {
        binding.addToCart.setOnClickListener {
            block()
        }
    }
}