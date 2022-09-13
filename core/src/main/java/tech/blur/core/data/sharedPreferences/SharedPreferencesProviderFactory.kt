package tech.blur.core.data.sharedPreferences

import dagger.assisted.AssistedFactory


@AssistedFactory
interface SharedPreferencesProviderFactory {
    fun create(prefsName: String): SharedPreferencesProviderImpl
}