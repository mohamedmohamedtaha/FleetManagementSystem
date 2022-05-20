package com.example.fleetmanagement.utils

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object Formatter {
    const val EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY = "EEE MMM dd HH:mm:ss zzz yyyy"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    private const val UTC = "UTC"
    // CREATE DateFormatSymbols WITH ALL SYMBOLS FROM (DEFAULT) Locale
    @SuppressLint("ConstantLocale")
    private val symbols: DateFormatSymbols = DateFormatSymbols(Locale.getDefault())

    fun format(dateTime:String, inFormat:String,outFormat:String, isUtc:Boolean = false):String?{
        return format(Locale.US,dateTime,inFormat,outFormat,isUtc)
    }
    fun format(locale:Locale,dateTime: String,inFormat: String,outFormat: String,isUtc: Boolean):String?{
        try {
            val input = SimpleDateFormat(inFormat,locale)
            if (isUtc)
                input.timeZone = TimeZone.getTimeZone(UTC)
            val date = input.parse(dateTime)
            val output = SimpleDateFormat(outFormat,locale)
            output.timeZone = TimeZone.getDefault()
            output.dateFormatSymbols = symbols
            return output.format(date)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return null
    }


}



















