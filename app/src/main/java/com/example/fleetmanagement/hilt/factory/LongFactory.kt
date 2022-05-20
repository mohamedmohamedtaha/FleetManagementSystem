package com.example.fleetmanagement.hilt.factory

import com.example.fleetmanagement.utils.TimerInterface
import com.example.fleetmanagement.utils.UtilCountDownTimer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface LongFactory {
    fun create(@Assisted timerInterface: TimerInterface,@Assisted("periodTime")periodTime: Long, @Assisted("countDownInterval")countDownInterval:Long):UtilCountDownTimer
}