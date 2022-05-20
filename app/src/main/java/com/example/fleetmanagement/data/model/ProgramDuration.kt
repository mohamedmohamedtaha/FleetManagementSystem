package com.example.fleetmanagement.data.model

data class ProgramDuration(
    val code: String,
    val created_at: String,
    val duration: Int,
    val duration_unit: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val name_ar: String,
    val name_en: String,
    val pricing_set: List<Prices>,
    val sort: Int
)