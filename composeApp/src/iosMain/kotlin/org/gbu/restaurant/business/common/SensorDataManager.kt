package org.gbu.restaurant.business.common

import kotlinx.coroutines.channels.Channel
import platform.CoreMotion.CMMotionManager
import platform.Foundation.NSOperationQueue

class SensorDataManager() {
    var motion = CMMotionManager()
    val data: Channel<SensorData> = Channel(Channel.UNLIMITED)

    fun startGyros() {
        if (motion.isGyroAvailable()){
            motion.gyroUpdateInterval = 1.0 / 50.0
            motion.startGyroUpdates()
            motion.startDeviceMotionUpdatesToQueue(NSOperationQueue.currentQueue!!){ mtn, error ->
                if (mtn != null){
                    val attitude = mtn.attitude
                    data.trySend(
                        SensorData(
                        roll = attitude.roll.toFloat(),
                        pitch = attitude.pitch.toFloat()
                    )
                    )
                }
            }
        }
    }

    fun stopGyros(){
        motion.stopGyroUpdates()
    }
}