package org.gbu.restaurant

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun mainViewController(
    mapUIViewController: () -> UIViewController,
    latitude: Double,
    longitude: Double
) = ComposeUIViewController {
    mapViewController = mapUIViewController
    globalLatitude = latitude
    globalLongitude = longitude
//    RestaurantApplication(TODO())
}

lateinit var mapViewController: () -> UIViewController
var globalLatitude: Double = 0.0
var globalLongitude: Double = 0.0