package com.example.fleetmanagement.data

object URLFactory {
    const val URL = "https://test.sharpfitness.fit/api/"
}

object Method {
    private const val USER = "users/"
    private const val MISCELLANEOUS = "miscellaneous/"
    const val LOGIN = USER + "login"
    const val REFRESH = LOGIN + "refresh"
    const val CREATE_ACCOUNT = USER + "create_profile"
    const val VERIFY_OTP = "$CREATE_ACCOUNT/verify_otp"
    const val GET_GENDER = USER + "genders"

    const val FORGOT_PASSWORD = USER + "forgot_password"
    const val RESET_PASSWORD = "$FORGOT_PASSWORD/reset_password"
    const val CHANGE_PASSWORD = USER + "change_password"

    const val REQUEST = "$CHANGE_PASSWORD/request"
    const val GET_PROFILE = USER + "profile"
    const val LOGOUT = USER + "logout"
    const val SUPPLEMENT_CATEGORIES = MISCELLANEOUS + "supplements_categories"
    const val SUPPLEMENT = MISCELLANEOUS + "supplements"
    private const val SERVICES = "services/"
    const val SUBSCRIBE = SERVICES + "subscribe"
    const val DISEASES = SERVICES + "diseases"
    const val PROGRAM_DURATION = SERVICES + "program_durations"
    const val PRICING = SERVICES + "pricing"
    const val DAILY_ACTIVITIES = SERVICES + "daily_activities"



}

