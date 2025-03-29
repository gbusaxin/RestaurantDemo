package org.gbu.restaurant.business.common

import kotlinx.coroutines.flow.MutableSharedFlow
import platform.Foundation.NSUserDefaults

actual suspend fun Context.getData(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}

actual suspend fun Context.puData(
    key: String,
    `object`: String
) {
    val sharedFlow = MutableSharedFlow<String>()
    NSUserDefaults.standardUserDefaults().setObject(`object`, key)
    sharedFlow.emit(`object`)
}
