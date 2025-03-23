package org.gbu.restaurant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.decompose.root.RestaurantRoot
import org.gbu.restaurant.sensor.SensorDataManager
import org.gbu.restaurant.sensor.SensorManagerImpl

@Composable
fun MainView(root: RestaurantRoot, appContext: Context) {

    val sensorManager = SensorManagerImpl()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val dataManager = SensorDataManager(context)
        dataManager.init()

        val job = scope.launch {
            dataManager.data
                .receiveAsFlow()
                .onEach { sensorManager.listener?.onUpdate(it) }
                .collect()
        }

        onDispose {
            dataManager.cancel()
            job.cancel()
        }
    }

    RestaurantApplication(root = root, sensorManager = sensorManager, context = appContext)
}
