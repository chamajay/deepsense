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
                "Angry" -> R.drawable.ic_angry_face
                "Disgusted" -> R.drawable.ic_nauseated_face
                "Scared" -> R.drawable.ic_fearful_face
                "Happy" -> R.drawable.ic_hugging_face
                "Meh" -> R.drawable.ic_neutral_face
                "Sad" -> R.drawable.ic_loudly_crying_face
                "Surprised" -> R.drawable.ic_face_with_open_mouth
                else -> 0 // no image resource ID
            }

            return Pair(mappedEmotion, imageResId)
        }

    }

}