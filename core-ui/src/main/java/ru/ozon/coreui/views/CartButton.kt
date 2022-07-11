package ru.ozon.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.srggrch.coreui.databinding.MergeCartButtonBinding

class CartButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = MergeCartButtonBinding.inflate(LayoutInflater.from(context), this)

    private var countListener: ((Int) -> Unit)? = null

    var text: CharSequence
        get() = binding.addToCart.text
        set(value) {
            binding.addToCart.text = value
        }

    var isInCart: Boolean = false
        set(value) {
            field = value
            if (value) {
                binding.addToCart.visibility = View.INVISIBLE
                binding.countGroup.isVisible = true
                if (count < 0)
                    count = 1
            } else {
                binding.addToCart.visibility = View.VISIBLE
                binding.countGroup.isVisible = false
            }
        }

    var count: Int = 0
        set(value) {
            field = value
            if (value <= 0) {
                field = 0
                isInCart = false
            }
            binding.count.text = value.toString()
        }

    init {
        binding.minus.setOnClickListener {
            count -= 1
            countListener?.invoke(count)
        }

        binding.plus.setOnClickListener {
            count += 1
            countListener?.invoke(count)
        }
//        binding.countGroup.referencedIds = intArrayOf(
//            binding.count.id,
//            binding.plus.id,
//            binding.minus.id
//        )

//        binding.countGroup.isVisible = false
    }

    fun setText(@StringRes textId: Int) {
        text = resources.getText(textId)
    }

    fun setOnClickListener(block: () -> Unit) {
        binding.addToCart.setOnClickListener {
            block()
        }
    }

    fun setCountListener(block: (count: Int) -> Unit) {
        countListener = block
    }
}