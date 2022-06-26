package ru.ozon.utils.ui

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object MoneyFormatter {
    private const val PATTERN = "###,###,### "

    fun format(price: String): String {
        val dfMoney = DecimalFormat(PATTERN)
        val dfs = DecimalFormatSymbols()
        dfs.groupingSeparator = ' '
        dfs.decimalSeparator = ' '
        dfMoney.decimalFormatSymbols = dfs
        return dfMoney.format(price.toDouble()) + '\u20BD'
    }
}