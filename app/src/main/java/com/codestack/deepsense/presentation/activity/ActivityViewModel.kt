package com.codestack.deepsense.presentation.activity

import androidx.lifecycle.ViewModel
import com.codestack.deepsense.core.Constants.BASE_URL
import com.codestack.deepsense.core.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class ActivityViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _typingActivity: MutableStateFlow<MutableMap<String, Map<String, String>>> =
        MutableStateFlow(
            mutableMapOf()
        )
    val typingActivity: StateFlow<MutableMap<String, Map<String, String>>>
        get() = _typingActivity


    fun retrieveTypingActivity() {
        scope.launch {
            val request = Request.Builder()
                .url("$BASE_URL/recent-text-activity")
                .build()

            try {
                // Execute request
                val response = client.newCall(request).execute()

                // Simulate a delay for loading animation
                delay(500)

                val responseBodyString = response.body?.string()
                responseBodyString?.let {
                    val jsonObj = JSONObject(responseBodyString)
                    val value = jsonObj.getString("recent_text_activity")

                    val jsonArr = JSONArray(value)
                    for (i in 0 until jsonArr.length()) {
                        val jsonObject = jsonArr.getJSONObject(i)
                        val text = jsonObject.getString("text")
                        val joy = jsonObject.getDouble("joy")
                        val surprise = jsonObject.getDouble("surprise")
                        val neutral = jsonObject.getDouble("neutral")
                        val sadness = jsonObject.getDouble("sadness")
                        val anger = jsonObject.getDouble("anger")
                        val disgust = jsonObject.getDouble("disgust")
                        val fear = jsonObject.getDouble("fear")
                        val primary =
                            Utils.mapEmotion(jsonObject.getString("primary_emotion")).first
                        val nonSuicidal = jsonObject.getDouble("suicidal_label_0")
                        val suicidal = jsonObject.getDouble("suicidal_label_1")
                        val suicideRisk = jsonObject.getString("suicide_risk")

                        val emotionsMap = mapOf(
                            "Happy" to joy.toString(),
                            "Surprised" to surprise.toString(),
                            "Meh" to neutral.toString(),
                            "Sad" to sadness.toString(),
                            "Angry" to anger.toString(),
                            "Disgusted" to disgust.toString(),
                            "Scared" to fear.toString(),
                            "Non-suicidal" to nonSuicidal.toString(),
                            "Suicidal" to suicidal.toString(),
                            "Primary" to primary,
                            "SuicideRisk" to capitalize(suicideRisk)
                        )

                        _typingActivity.value =
                            _typingActivity.value.toMutableMap().apply {
                                put(text, emotionsMap)
                            }

                    }
                }
            } catch (_: Exception) { }
        }
    }

    private fun capitalize(word: String): String {
        if (word.isEmpty()) {
            return word
        }

        val firstLetter = word[0].uppercaseChar()
        val restOfWord = word.substring(1)

        return "$firstLetter$restOfWord"
    }
}