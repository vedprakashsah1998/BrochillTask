package com.infinty8.brochilltask.remote.datasource

import com.infinty8.brochilltask.model.LoginPostModel
import com.infinty8.brochilltask.model.RegisterPostBody
import com.infinty8.brochilltask.network.RetroService
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
class LoginDataSource @Inject constructor(
    private val service: RetroService,
) {
    suspend fun loginData(loginPostModel: LoginPostModel) =
        service.loginUser(loginPostModel)

}