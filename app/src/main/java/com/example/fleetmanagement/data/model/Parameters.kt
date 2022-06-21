package com.example.fleetmanagement.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

class Parameters @Inject constructor() {
    @SerializedName("username")
    @Expose
    var userName: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("Password")
    @Expose
    var password: String? = null

    @SerializedName("Email")
    @Expose
    var email: String? = null

    @SerializedName("password1")
    @Expose
    var password1: String? = null

    @SerializedName("password2")
    @Expose
    var password2: String? = null

    @SerializedName("new_password1")
    @Expose
    var newPassword1: String? = null


    @SerializedName("old_password")
    @Expose
    var oldPassword: String? = null

    @SerializedName("new_password2")
    @Expose
    var newPassword2: String? = null

    @SerializedName("gender_id")
    @Expose
    var genderId: String? = null

    @SerializedName("otp")
    @Expose
    var OTP: String? = null

    @SerializedName("access")
    @Expose
    var access: String? = null

    @SerializedName("supplement_category_id")
    @Expose
    var supplementCategoryId: String? = null

    @SerializedName("training_types")
    @Expose
    var trainingTypes: ArrayList<String>? = null

    @SerializedName("program_types")
    @Expose
    var programTypes: ArrayList<String>? = null


    @SerializedName("program_duration")
    @Expose
    var programDuration: String? = null

    @SerializedName("birth_date")
    @Expose
    var birthDate: String? = null


    @SerializedName("weight")
    @Expose
    var weight: String? = null


    @SerializedName("height")
    @Expose
    var height: String? = null


    @SerializedName("daily_activity")
    @Expose
    var dailyActivity: String? = null


    @SerializedName("body_fat")
    @Expose
    var bodyFat: String? = null


    @SerializedName("body_muscle")
    @Expose
    var bodyMuscle: String? = null

    @SerializedName("body_water")
    @Expose
    var bodyWater: String? = null


    @SerializedName("bmi")
    @Expose
    var bmi: String? = null


    @SerializedName("disease")
    @Expose
    var disease: String? = null


    @SerializedName("medical_history")
    @Expose
    var medicalHistory: String? = null

    @SerializedName("program_duration_code")
    @Expose
    var programDurationCode: String? = null


}