package com.codestack.deepsense.core

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
                "neutral" -> "Meh"
                "sadness" -> "Sad"
                "surprise" -> "Surprised"
                else -> "unknown"
            }

            val imageResId = when (mappedEmotion) {
                "Angry" -> R.raw.angry_lottie
                "Disgusted" -> R.raw.disgusted_lottie
                "Scared" -> R.raw.fear_lottie
                "Happy" -> R.raw.joy_lottie
                "Meh" -> R.raw.meh_lottie
                "Sad" -> R.raw.sad_lottie
                "Surprised" -> R.raw.surprise_lottie
                else -> 0 // no image resource ID
            }

            return Pair(mappedEmotion, imageResId)
        }

    }

}