package com.infinty8.brochilltask.remote.repository

import com.infinty8.brochilltask.remote.datasource.WelcomeDataSource
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
class WelcomeUserRepo @Inject constructor(
    private val welcomeDataSource: WelcomeDataSource,
) {
    suspend fun welcomeUser(appToken: String) =
        welcomeDataSource.welcomeUser(appToken)

}