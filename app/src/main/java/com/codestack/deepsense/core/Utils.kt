package com.codestack.deepsense.core

import android.util.Log
import com.codestack.deepsense.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception) {
            Log.e(TAG, e.message ?: e.toString())
        }
    }
}