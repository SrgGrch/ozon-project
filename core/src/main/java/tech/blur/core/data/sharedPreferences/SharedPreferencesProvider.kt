package tech.blur.core.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

interface SharedPreferencesProvider {
    fun provide(): SharedPreferences
}

class SharedPreferencesProviderImpl @AssistedInject constructor(
    private val context: Context,
    @Assisted private val prefsName: String
) : SharedPreferencesProvider {
    override fun provide(): SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
}