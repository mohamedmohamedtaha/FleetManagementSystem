package com.example.fleetmanagement.data.model

data class SubscriptionReportSet(
    val bmi: String,
    val body_fat: String,
    val body_muscle: String,
    val body_water: String,
    val created_at: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val subscription: String,
    val weight: String
)