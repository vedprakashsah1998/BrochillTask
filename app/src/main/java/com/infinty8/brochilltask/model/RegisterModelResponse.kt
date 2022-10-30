package com.infinty8.brochilltask.model

import com.google.gson.annotations.SerializedName

data class RegisterModelResponse(
    @SerializedName("first_name") val first_name: String,
    @SerializedName("last_name") val last_name: String,
    @SerializedName("_id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("token") val token: String
)
