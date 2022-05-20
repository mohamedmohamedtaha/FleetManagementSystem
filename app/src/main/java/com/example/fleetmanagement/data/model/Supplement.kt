package com.example.fleetmanagement.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Supplement(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("supplement_category")
    @Expose
    val supplement_category: SupplementCategories,
    @SerializedName("supplement_fact_set")
    @Expose
    val supplementFactSet: List<SupplementFactSet>,
    @SerializedName("name_en")
    @Expose
    val nameEn: String,
    @SerializedName("name_ar")
    @Expose
    val nameAr: String,
    @SerializedName("supplement_image")
    @Expose
    val supplementImage: String,
    @SerializedName("description_en")
    @Expose
    val descriptionEn: String,
    @SerializedName("description_ar")
    @Expose
    val descriptionAr: String,
    @SerializedName("is_present")
    @Expose
    val isPresent: Boolean,
    @SerializedName("created_at")
    @Expose
    val createdAt: String,
    @SerializedName("last_modified_at")
    @Expose
    val lastModifiedAt: String
) : Parcelable