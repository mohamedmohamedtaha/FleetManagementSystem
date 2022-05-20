package com.example.fleetmanagement.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Gender(@SerializedName("id")
             @Expose
              val id :String,
@SerializedName("name_en")
@Expose
 val nameEn:String,
@SerializedName("name_ar")
@Expose
 val nameAr:String,
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
 val lastModifiedAt:String)
