package com.example.fleetmanagement.data.api

class ResponseBody<T>(val code: Int,val message:String, val detail: T? )