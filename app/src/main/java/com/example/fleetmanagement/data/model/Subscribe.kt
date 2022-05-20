package com.example.fleetmanagement.data.model

data class Subscribe(
    val created_at: String,
    val daily_activity: String,
    val id: String,
    val is_present: Boolean,
    val last_modified_at: String,
    val medical_history: String,
    val profile: String,
    val program_duration: String,
    val subscription_disease_set: List<SubscriptionDiseaseSet>,
    val subscription_program_type_set: List<SubscriptionProgramTypeSet>,
    val subscription_report_set: List<SubscriptionReportSet>,
    val subscription_training_type_set: List<SubscriptionTrainingTypeSet>
)