package com.example.githubuser.ui.theme


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreference private constructor(private val dataStore: DataStore<Preferences>){

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDakModeActive: Boolean){
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDakModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE_THEME: SettingPreference? = null

        fun getInstanceTheme(dataStore: DataStore<Preferences>): SettingPreference {
            return INSTANCE_THEME ?: synchronized(this) {
                val instanceTheme = SettingPreference(dataStore)
                INSTANCE_THEME = instanceTheme
                instanceTheme
            }
        }
    }
}