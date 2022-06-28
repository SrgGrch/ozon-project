package ru.ozon.coreui

import android.content.Context
import android.content.res.Resources
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

sealed interface PrintableText : Parcelable {

    fun getText(resource: Resources): String

    @Parcelize
    data class Raw(val string: String) : PrintableText {
        override fun getText(resource: Resources) = string
    }

    @Parcelize
    data class StringResource(
        @StringRes val resId: Int,
        val formatArgs: @RawValue List<Any?>
    ) : PrintableText {
        constructor(@StringRes resId: Int, vararg formatArgs: Any?)
                : this(resId, formatArgs.toList())

        override fun getText(resource: Resources): String {
            return resource.getString(
                resId,
                *formatArgs.toTypedArray(),
            )
        }
    }

    @Parcelize
    data class PluralResource(
        @PluralsRes val resId: Int,
        val quantity: Int,
        val formatArgs: @RawValue List<Any?>,
    ) : PrintableText {
        constructor(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any?)
                : this(resId, quantity, formatArgs.toList())

        override fun getText(resource: Resources): String {
            return resource.getQuantityString(
                resId,
                quantity,
                *formatArgs.toTypedArray(),
            )
        }
    }

    fun isEmpty(): Boolean {
        return when (this) {
            is Raw -> this == Raw("")
            else -> false
        }
    }
}


fun Resources.getPrintableText(printableText: PrintableText): String = printableText.getText(this)

fun Context.getPrintableText(printableText: PrintableText) =
    resources.getPrintableText(printableText)

fun Fragment.getPrintableText(printableText: PrintableText) =
    resources.getPrintableText(printableText)

fun TextView.setPrintableText(printableText: PrintableText) {
    text = resources.getPrintableText(printableText)
}

fun TextView.setPrintableTextOrGone(printableText: PrintableText?) {
    visibility = if (printableText != null) View.VISIBLE else View.GONE
    text = printableText?.let(resources::getPrintableText)
}