package com.example.fleetmanagement.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class SupplementFactSet(@SerializedName("id")
@Expose
val id:String,
@SerializedName("fact")
@Expose
val fact :Fact,
@SerializedName("amount")
@Expose
val amount:String,
@SerializedName("sort")
@Expose
val sort:Int,
@SerializedName("is_present")
@Expose
val isPresent:Boolean,
@SerializedName("created_at")
@Expose
val createdAt:String,
@SerializedName("last_modified_at")
@Expose
val lastModifiedAt:String,
@SerializedName("supplement")
@Expose
val supplement:String):Parcelable