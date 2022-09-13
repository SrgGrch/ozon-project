package tech.blur.coreui

import android.content.res.Resources
import kotlin.math.ceil

/** Returns the given pixel value in dp  */
val Int.dp: Int get() = ceil(this * Resources.getSystem().displayMetrics.density).toInt()
val Float.dp: Float get() = this * Resources.getSystem().displayMetrics.density