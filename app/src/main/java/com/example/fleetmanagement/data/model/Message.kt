package com.example.fleetmanagement.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val Arrived: String,
    val CarType: String,
    val CatName: String,
    val DateFrom: String,
    val DateTo: String,
    val DriverName: String,
    val EndKM: String,
    val FormStart: String,
    val RequestFile: String,
    val Root: String,
    val StartKM: String
):Parcelable