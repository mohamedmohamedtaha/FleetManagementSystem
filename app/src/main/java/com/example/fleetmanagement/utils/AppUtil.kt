package com.example.fleetmanagement.utils

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.lang.Exception

object AppUtil {
    fun getColorSpannable(
        context: Context, spannableString: String,
        message: SpannableString, color: Int
    ): SpannableString {

        try {
            val outPutString = SpannableString(message)
            outPutString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, color)),
                message.indexOf(spannableString),
                message.indexOf(spannableString) + spannableString.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return outPutString
        } catch (e: Exception) {

        }
        return SpannableString("")
    }
    fun getFontSpannableString(
        context: Context,
        spannableString: String,
        message: SpannableString,
        font: Int
    ): SpannableString {
        try {
            val outPutString = SpannableString(message)
            outPutString.setSpan(
                CustomTypeFace("", ResourcesCompat.getFont(context, font)!!),
                message.indexOf(spannableString),
                message.indexOf(spannableString) + spannableString.length,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return outPutString
        } catch (e: Exception) {

        }
        return SpannableString("")
    }

    fun getUnderLineText(
        spannableString: String,
        message: SpannableString
    ): SpannableString {
        val outPutSpannable = SpannableString(message)
        outPutSpannable.setSpan(
            UnderlineSpan(), message.indexOf(spannableString),
            message.indexOf(spannableString) + spannableString.length,
            0
        )
        return outPutSpannable
    }

//    fun getColorSpannable(context: Context,spannableString: String,
//    message:SpannableString,color:Int):SpannableString{
//        try {
//            val outPutString = SpannableString(message)
//            outPutString.setSpan(
//                ForegroundColorSpan(ContextCompat.getColor(context,color)),
//                message.indexOf(spannableString),
//                message.indexOf(spannableString) + spannableString.length,
//                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
//            return outPutString
//
//        }catch (e:Exception){
//
//        }
//        return SpannableString("")
//    }
}