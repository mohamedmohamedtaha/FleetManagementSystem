package com.example.fleetmanagement

import retrofit2.Response

class DataWrapper<T>(val responseBody:Response<T>?,val throwable: Throwable?)