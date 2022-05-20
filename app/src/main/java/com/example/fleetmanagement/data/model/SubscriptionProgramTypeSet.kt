package com.example.fleetmanagement.data.model

data class SubscriptionProgramTypeSet(
    val created_at: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val program_type: ProgramType,
    val subscription: String
)