package com.infinty8.brochilltask.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.infinty8.brochilltask.storage.PrefConstant.fileName
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Ved Prakash
 * @created 29/10/2022
 */
@Singleton
class AppPref @Inject constructor(@ApplicationContext val context: Context) {


    fun setValue(key: String, value: String) {
        getSecretSharedPref(context).edit().apply {
            putString(key, value)
        }.apply()
    }

    fun getValue(key: String, defValue: String): String? {
        return getSecretSharedPref(context).getString(key, defValue)
    }


    private fun getSecretSharedPref(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            fileName,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}