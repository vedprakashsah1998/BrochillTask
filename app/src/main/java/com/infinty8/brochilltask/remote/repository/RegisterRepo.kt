package com.infinty8.brochilltask.remote.repository

import com.infinty8.brochilltask.model.RegisterPostBody
import com.infinty8.brochilltask.remote.datasource.RegisterDataSource
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
class RegisterRepo @Inject constructor(
    private val registerDataSource: RegisterDataSource
) {
    suspend fun registerUser(registerPostBody: RegisterPostBody) =
        registerDataSource.registerUser(registerPostBody)

}