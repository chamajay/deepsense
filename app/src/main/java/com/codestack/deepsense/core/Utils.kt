package com.codestack.deepsense.core

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.codestack.deepsense.R
import com.codestack.deepsense.core.Constants.TAG
import java.util.*

class Utils {
    companion object {
        fun print(e: Exception) {
            Log.e(TAG, e.message ?: e.toString())
        }

        fun mapEmotion(emotion: String): Pair<String, Int> {
            val mappedEmotion = when (emotion.lowercase(Locale.ROOT)) {
                "anger" -> "Angry"
                "disgust" -> "Disgusted"
                "fear" -> "Scared"
                "joy" -> "Happy"
                "neutral" -> "Neutral"
                "sadness" -> "Sad"
                "surprise" -> "Surprised"
                else -> "unknown"
            }

            val imageResId = when (mappedEmotion) {
                "Angry" -> R.raw.angry_lottie
                "Disgusted" -> R.raw.disgusted_lottie
                "Scared" -> R.raw.fear_lottie
                "Happy" -> R.raw.joy_lottie
                "Neutral" -> R.raw.meh_lottie
                "Sad" -> R.raw.sad_lottie
                "Surprised" -> R.raw.surprise_lottie
                else -> 0 // no image resource ID
            }

            return Pair(mappedEmotion, imageResId)
        }

        fun sharedPrefPutValue(context: Context, key: String, value: Any?) {
            val prefs: SharedPreferences =
                context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> editor.putLong(key, value)
                is Float -> editor.putFloat(key, value)
                is Boolean -> editor.putBoolean(key, value)
                else -> editor.remove(key)
            }
            editor.apply()
        }

        fun sharedPrefGetValue(context: Context, key: String): Any? {
            val prefs: SharedPreferences =
                context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            return when (val value = prefs.all[key]) {
                is String -> value
                is Int -> value
                is Long -> value
                is Float -> value
                is Boolean -> value
                else -> null
            }
        }

    }

}