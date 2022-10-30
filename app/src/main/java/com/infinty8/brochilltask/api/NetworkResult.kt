package com.infinty8.brochilltask.api

import okio.IOException

/**
 * @author NetworkResult
 * @created 10/29/2022
 */
sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String) : NetworkResult<T>()
}