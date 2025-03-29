package org.gbu.restaurant.business.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import org.gbu.restaurant.business.core.APP_DATASTORE

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(APP_DATASTORE)

actual suspend fun Context.getData(key: String): String? {
    return dataStore.data.first()[stringPreferencesKey(key)] ?: ""
}

actual suspend fun Context.puData(
    key: String,
    `object`: String
) {
    dataStore.edit {
        it[stringPreferencesKey(key)] = `object`
    }
}