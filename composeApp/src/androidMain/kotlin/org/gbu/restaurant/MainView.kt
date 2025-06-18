package org.gbu.restaurant

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.decompose.root.RestaurantRoot
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.gbu.restaurant.sensor.SensorDataManager
import org.gbu.restaurant.sensor.SensorManagerImpl

@Composable
fun MainView(appContext: Context, root: RestaurantRootImpl) {
    RestaurantApplication(context = appContext, root = root)
}
