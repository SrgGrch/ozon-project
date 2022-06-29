package ru.ozon.addproduct.ui.validators

import android.webkit.URLUtil
import javax.inject.Inject

class ImageUrlValidator @Inject constructor(): Validator<String?> {
    override fun validate(value: String?): Boolean {
        if (value == null) return false
        return URLUtil.isValidUrl(value)
    }
}