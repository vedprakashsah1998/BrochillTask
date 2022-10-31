package com.infinty8.brochilltask.model

import com.google.gson.annotations.SerializedName

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
data class WelcomeUserResponse(

    @SerializedName("message") val message: String,
)