package com.example.fleetmanagement.data.model

data class SubscriptionTrainingTypeSet(
    val created_at: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val subscription: String,
    val training_type: TrainingType
)