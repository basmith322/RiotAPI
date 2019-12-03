package com.example.riotapi.Utilities

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeMethod : SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    var shakeThresholdGravity: Float = 2f
    var lastUpdateTime: Long = 0
    var callback: (() -> Void)? = null

    fun ShakeShakeShakeSenora(sensorManager: SensorManager, callback: (() -> Void)?) {
        this.sensorManager = sensorManager
        sensorManager.registerListener(this, sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        lastUpdateTime = System.currentTimeMillis()
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        this.callback = callback
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        when (accuracy) {
            SensorManager.SENSOR_STATUS_UNRELIABLE -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_LOW -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> {
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values: FloatArray = event.values

        val x = values[0]
        val y = values[0]
        val z = values[1]

        val gX = x / SensorManager.GRAVITY_EARTH
        val gY = y / SensorManager.GRAVITY_EARTH
        val gZ = z / SensorManager.GRAVITY_EARTH

        val gForce: Float = sqrt(gX * gX + gY * gY + gZ * gZ)

        val currentTime: Long = System.currentTimeMillis()

        if (gForce >= shakeThresholdGravity) {
            if (currentTime - lastUpdateTime < 200) {
                return
            }
            lastUpdateTime = currentTime
            this.callback?.invoke()
        }
    }
}