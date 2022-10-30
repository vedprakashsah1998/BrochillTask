package com.infinty8.brochilltask.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarUtils {
    fun showMessage(view: View, message: String) =
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}