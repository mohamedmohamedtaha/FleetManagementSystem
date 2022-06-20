package com.example.fleetmanagement.data.api

import com.example.fleetmanagement.data.Method
import com.example.fleetmanagement.data.model.*
import retrofit2.http.*

interface AuthenticationService {
    //@POST(Method.LOGIN)
    //@FormUrlEncoded
    @GET("api")
    suspend fun login(@Query("Email") username:String, @Query("Password") password:String, @Query("type") type:String): LoginSuccess
    @GET("api")
    suspend fun getTasksList(@Query("Email") username:String, @Query("Password") password:String, @Query("type") type:String): List<Task>

    @POST(Method.FORGOT_PASSWORD)
    suspend fun forgetPassword(@Body parameters: Parameters): ResponseBody<Any>

    @POST(Method.RESET_PASSWORD)
    suspend fun resetPassword(@Body parameters: Parameters): ResponseBody<Any>

    @POST(Method.REQUEST)
    suspend fun request(): ResponseBody<Any>

    @POST(Method.SUBSCRIBE)
    suspend fun subscribe(@Body parameters: Parameters): ResponseBody<Subscribe>

    @GET(Method.DISEASES)
    suspend fun getDiseases(): ResponseBody<List<Disease>>

    @GET(Method.PRICING)
    suspend fun getPricing(@Body parameters: Parameters): ResponseBody<Prices>

    @GET(Method.DAILY_ACTIVITIES)
    suspend fun getDailyActivity(): ResponseBody<List<DailyActivities>>

    @GET(Method.PROGRAM_DURATION)
    suspend fun getProgramDuration(): ResponseBody<List<ProgramDuration>>

    @GET(Method.GET_PROFILE)
    suspend fun getProfile(): ResponseBody<CreateAccount>

    @POST(Method.LOGOUT)
    suspend fun logOut(@Body parameters: Parameters): ResponseBody<Any>


    @POST(Method.CHANGE_PASSWORD)
    suspend fun changePassword(@Body parameters: Parameters): ResponseBody<Any>

    @POST(Method.CREATE_ACCOUNT)
    suspend fun createAccount(@Body parameters: Parameters): ResponseBody<CreateAccount>

    @POST(Method.REFRESH)
    suspend fun refresh(@Body parameters: Parameters): ResponseBody<Any>

    @POST(Method.VERIFY_OTP)
    suspend fun verificationCode(@Body parameters: Parameters): ResponseBody<Any>

    @GET(Method.SUPPLEMENT_CATEGORIES)
    suspend fun getSupplementCategories(): ResponseBody<List<SupplementCategories>>

    @GET(Method.SUPPLEMENT)
    suspend fun getSupplementById(@QueryMap params: Map<String, String>): ResponseBody<List<Supplement>>
//    suspend fun getSupplementById(@Query("supplement_category_id") supplement_category_id: String): ResponseBody<List<Supplement>>


}