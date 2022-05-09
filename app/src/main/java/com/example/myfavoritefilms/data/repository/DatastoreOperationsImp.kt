package com.example.myfavoritefilms.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myfavoritefilms.domain.repository.DatastoreOperations
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException


val Context.datastore:DataStore<Preferences> by preferencesDataStore(name = "boarding_datastore")

class DatastoreOperationsImp(context: Context): DatastoreOperations {

    private val dataStore = context.datastore

    private object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey("boarding_pref_key")
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception->
                if(exception is IOException){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }

            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey]?:false
                onBoardingState
            }

    }
}