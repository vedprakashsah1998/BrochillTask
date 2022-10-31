package com.infinty8.brochilltask.remote.datasource

import com.infinty8.brochilltask.model.RegisterPostBody
import com.infinty8.brochilltask.network.RetroService
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
class RegisterDataSource @Inject constructor(
    private val service: RetroService,
) {
    suspend fun registerUser(registerPostBody: RegisterPostBody) =
        service.registerCall(registerPostBody)

}