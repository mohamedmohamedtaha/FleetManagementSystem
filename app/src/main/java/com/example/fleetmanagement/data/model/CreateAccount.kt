package com.example.fleetmanagement.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CreateAccount(
@SerializedName("id")
@Expose
 val id: String? = null,
@SerializedName("user")
@Expose
 val user: User? = null,

@SerializedName("gender")
@Expose
 val gender: Gender? = null,

@SerializedName("name")
@Expose
 val name: String? = null,

@SerializedName("profile_image")
@Expose
 val profileImage: String? = null,

@SerializedName("birth_date")
@Expose
 val birthDate: Any? = null,

@SerializedName("weight")
@Expose
 val weight: String? = null,

@SerializedName("height")
@Expose
 val height: String? = null,

@SerializedName("is_present")
@Expose
 val isPresent: Boolean? = null,

@SerializedName("created_at")
@Expose
 val createdAt: String? = null,

@SerializedName("last_modified_at")
@Expose
 val lastModifiedAt: String? = null )