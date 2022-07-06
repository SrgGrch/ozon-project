package com.srggrch.core.data.sharedPreferences

import dagger.assisted.AssistedFactory


@AssistedFactory
interface SharedPreferencesProviderFactory {
    fun create(prefsName: String): SharedPreferencesProviderImpl
}