package org.gbu.restaurant.business.common

class SensorManagerImpl : SensorManager {
    var listener: Listener? = null
    override fun registerListener(listener: Listener) {
        this.listener = listener
    }
}