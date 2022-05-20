package com.example.fleetmanagement.utils

import android.os.CountDownTimer
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class UtilCountDownTimer @AssistedInject
    constructor(@Assisted private val timerInterface: TimerInterface,
                @Assisted("periodTime") private val periodTime:Long,
                @Assisted("countDownInterval") private val countDownInterval:Long) {
        private var timer: CountDownTimer?= null
    fun start(){
        val time:Long = periodTime * countDownInterval
        timer= object :CountDownTimer(time,countDownInterval){
            override fun onTick(p0: Long) {
                timerInterface.start(p0)
            }

            override fun onFinish() {
              timerInterface.finish()
                stop()
            }
        }
        timer?.start()
    }
    fun stop(){
        if (timer != null){
            timer?.cancel()
            timer = null
        }
    }
}