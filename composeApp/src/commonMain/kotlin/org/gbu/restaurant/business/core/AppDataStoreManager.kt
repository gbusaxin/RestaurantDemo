package org.gbu.restaurant.business.core

import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.common.getData
import org.gbu.restaurant.business.common.puData

const val APP_DATASTORE = "org.gbu.restaurant"

class AppDataStoreManager(val context: Context):AppDataStore {
    override suspend fun setValue(key: String, value: String) {
        context.puData(key, value)
    }

    override suspend fun readValue(key: String): String? {
        return context.getData(key)
    }

}