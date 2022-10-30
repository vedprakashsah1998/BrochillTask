package com.infinty8.brochilltask.model

import com.google.gson.annotations.SerializedName

data class RegisterPostBody(
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
