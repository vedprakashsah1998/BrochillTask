package com.infinty8.brochilltask.remote.repository

import com.infinty8.brochilltask.model.LoginPostModel
import com.infinty8.brochilltask.remote.datasource.LoginDataSource
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
class LoginRepo @Inject constructor(
    private val loginDataSource: LoginDataSource,
) {
    suspend fun loginUser(loginPostModel: LoginPostModel) =
        loginDataSource.loginData(loginPostModel)

}