package org.gbu.restaurant

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.business.common.SensorDataManager
import org.gbu.restaurant.business.common.SensorManagerImpl
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.koin.core.KoinApplication
import platform.UIKit.UIViewController

fun mainViewController(
    mapUIViewController: () -> UIViewController,
    latitude: Double,
    longitude: Double,
    koinApplication: KoinApplication
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    globalLatitude = latitude
    globalLongitude = longitude

    val sensorManager = SensorManagerImpl()
    val scope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        val dataManager = SensorDataManager()
        dataManager.startGyros()

        val job = scope.launch {
            dataManager.data
                .receiveAsFlow()
                .onEach { sensorManager.listener?.onUpdate(it) }
                .collect {}
        }

        onDispose {
            dataManager.stopGyros()
            job.cancel()
        }
    }

    val root = RestaurantRootImpl(
        DefaultComponentContext(lifecycle = LifecycleRegistry()),
        koinApplication
    )

    RestaurantApplication(
        context = Context(),
        root = root
    )
}

val context = Context()
lateinit var mapViewController: () -> UIViewController
var globalLatitude: Double = 0.0
var globalLongitude: Double = 0.0