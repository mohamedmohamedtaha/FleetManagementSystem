package com.example.fleetmanagement.data.model

data class SubscriptionDiseaseSet(
    val created_at: String,
    val disease: Disease,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val subscription: String
)