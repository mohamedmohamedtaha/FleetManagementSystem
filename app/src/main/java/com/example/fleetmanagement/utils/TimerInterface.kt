package com.example.fleetmanagement.utils

interface TimerInterface {
    fun start(millisUntilFinished: Long)
    fun finish()
}