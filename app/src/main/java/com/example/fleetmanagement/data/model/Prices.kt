package com.example.fleetmanagement.data.model

data class Prices(
    val amount: String,
    val created_at: String,
    val discount_amount: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val program_duration: String,
    val sort: Int
)