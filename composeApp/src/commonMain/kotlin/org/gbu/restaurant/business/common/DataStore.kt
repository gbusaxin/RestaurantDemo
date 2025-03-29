package org.gbu.restaurant.business.common

expect suspend fun Context.puData(key: String, `object`: String)
expect suspend fun Context.getData(key: String): String?