package com.example.fleetmanagement.utils

class ApplicationException constructor(override var message:String) :Throwable()
{
    var type:Type? = null
    enum class Type{
        VALIDATION
    }
}
