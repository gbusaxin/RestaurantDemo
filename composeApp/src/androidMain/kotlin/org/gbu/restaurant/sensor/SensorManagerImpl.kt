package org.gbu.restaurant.sensor

import org.gbu.restaurant.ui.sensor.Listener
import org.gbu.restaurant.ui.sensor.SensorManager

class SensorManagerImpl : SensorManager {
    var listener: Listener? = null

    override fun registerListener(listener: Listener) {
        this.listener = listener
    }
}