package com.example.fleetmanagement.utils

import android.util.Log
import android.util.Patterns
import android.widget.CheckBox
import android.widget.TextView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Validator @Inject constructor() {
    private var messageBuilders: MutableList<MessageBuilder> = ArrayList()
    private var subject: String? = null
    private var editText: TextView? = null
    private var checkBox: CheckBox?= null
    fun submit(s: TextView): Validator {
        subject = s.text.toString().trim { it <= ' ' }
        editText = s
        messageBuilders = ArrayList()
        return this
    }
    fun submit(checkBox: CheckBox):Validator{
       // checkBox =
        messageBuilders = ArrayList()
        return this
    }

    enum class Type {
        Empty, Email, MinLength, MaxLength, Match, MatchNot, PatternMatch, EmptyWithoutTrim, Zero
    }

    //    sealed class Type{
//        class Empty:Type()
//        class Email:Type()
//        class MinLength():Type()
//        class MaxLength():Type()
//        class Match():Type()
//        class MatchNot():Type()
//        class PatternMatch():Type()
//        class EmptyWithoutTrim():Type()
//        class Phone():Type()
//        class Zero():Type()
//    }
    inner class MessageBuilder {
        var type: Type? = null
        var message: String? = null

        internal var validLength: Int = 0
        internal var match: String? = null

        internal constructor(type: Type) {
            this.type = type
        }

        internal constructor(type: Type, validLength: Int) {
            this.type = type
            this.validLength = validLength
        }

        internal constructor(type: Type, match: String) {
            this.type = type
            this.match = match
        }

        fun errorMessage(message: String): Validator {
            this.message = message
            messageBuilders.add(this)
            return this@Validator
        }

        fun errorMessage(message: Int): Validator {
            this.message = message.toString()
            messageBuilders.add(this)
            return this@Validator
        }

    }

    fun checkEmpty(): MessageBuilder {
        return MessageBuilder(Type.Empty)
    }

    fun checkEmail(): MessageBuilder {
        return MessageBuilder(Type.Email)
    }

//    fun checkPhone(): MessageBuilder {
//        return MessageBuilder(Type.Phone)
//    }

    fun checkMaxLength(max: Int): MessageBuilder {
        return MessageBuilder(Type.MaxLength, max)
    }

    fun checkMinLength(min: Int): MessageBuilder {
        return MessageBuilder(Type.MinLength, min)
    }

    fun checkMatch(string: String): MessageBuilder {
        return MessageBuilder(Type.Match, string)
    }

    fun checkNotMatch(string: String): MessageBuilder {
        return MessageBuilder(Type.MatchNot, string)
    }

    fun patternMatch(string: String): MessageBuilder {
        return MessageBuilder(Type.PatternMatch, string)
    }

    fun checkZero(): MessageBuilder {
        return MessageBuilder(Type.Zero)
    }

    @Throws(ApplicationException::class)
    private fun isEmpty(subject: String?, errorMessage: String?, byTrimming: Boolean) {
        var subjectVar: String = subject ?: throw ApplicationException(errorMessage!!)
        if (byTrimming)
            subjectVar = subjectVar.trim { it <= ' ' }
        if (subjectVar.isEmpty())
            throw ApplicationException(errorMessage!!)
    }

    @Throws(ApplicationException::class)
    private fun checkMinDigits(subject: String, errorMessage: String, min: Int) {
        if (subject.length < min)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun checkMAxDigits(subject: String, errorMessage: String, max: Int) {
        if (subject.length > max)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun isValidEmpty(subject: String, errorMessage: String) {
        if (!subject.matches(Patterns.EMAIL_ADDRESS.pattern().toRegex()))
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun match(subject: String?, toMatch: String?, errorMessage: String) {
        if (toMatch == null || subject != toMatch)
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun notMatch(subject: String?, toMatch: String?, errorMessage: String) {
        if (toMatch == null || subject == toMatch) {
            throw ApplicationException(errorMessage)
        }
    }

    @Throws(ApplicationException::class)
    private fun matchPatter(subject: String?, pattern: String?, errorMessage: String) {
        if (subject == null || !subject.matches(pattern!!.toRegex()))
            throw ApplicationException(errorMessage)
    }

    @Throws(ApplicationException::class)
    private fun zeroNotAllow(subject: String?, errorMessage: String) {
        val subjectVar: String = subject ?: throw ApplicationException(errorMessage)
        if (subjectVar.startsWith("0"))
            throw ApplicationException(errorMessage)
    }


    @Throws(ApplicationException::class)
    fun check(): Boolean {
        for (builder in messageBuilders) {
            try {
                when (builder.type) {
                    Type.Empty -> isEmpty(subject, builder.message, true)
                    Type.EmptyWithoutTrim -> isEmpty(subject, builder.message, false)
                    Type.Email -> isValidEmpty(subject!!, builder.message!!)
                    Type.MinLength -> checkMinDigits(
                        subject!!,
                        builder.message!!,
                        builder.validLength
                    )
                    Type.MaxLength -> checkMAxDigits(
                        subject!!,
                        builder.message!!,
                        builder.validLength
                    )
                    Type.Match -> match(subject!!, builder.match, builder.message!!)
                    Type.MatchNot -> notMatch(subject!!, builder.match, builder.message!!)
                    Type.PatternMatch -> matchPatter(subject!!, builder.match, builder.message!!)

                    Type.Zero -> zeroNotAllow(subject, builder.message!!)
                }
            } catch (e: ApplicationException) {
                e.printStackTrace()
                editText?.requestFocus()
                editText?.error = e.message
                Log.d("LoginFragment", "${e.message} ")
                e.type = ApplicationException.Type.VALIDATION
                throw e
            }
        }
        return true
    }
}








