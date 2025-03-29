package org.gbu.restaurant.sensor

import org.gbu.restaurant.business.common.Listener
import org.gbu.restaurant.business.common.SensorManager

class SensorManagerImpl : SensorManager {
    var listener: Listener? = null

    override fun registerListener(listener: Listener) {
        this.listener = listener
    }
}