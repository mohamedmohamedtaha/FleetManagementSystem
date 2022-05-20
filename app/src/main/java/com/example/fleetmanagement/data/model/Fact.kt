package com.example.fleetmanagement.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Fact(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name_en")
    @Expose
    val nameEn: String,
    @SerializedName("name_ar")
    @Expose
    val nameAr: String,
    @SerializedName("unit_en")
    @Expose
    val unitEn: String,
    @SerializedName("unit_ar")
    @Expose
    val unitAr: String,
    @SerializedName("is_present")
    @Expose
    val isPresent: Boolean,
    @SerializedName("created_at")
    @Expose
    val createdAt: String,
    @SerializedName("last_modified_at")
    @Expose
    val lastModifiedAt: String
):Parcelable