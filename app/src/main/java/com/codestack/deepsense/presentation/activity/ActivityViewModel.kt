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

    private val _typingActivityList: MutableStateFlow<MutableList<Map<String, Any>>> =
        MutableStateFlow(
            mutableListOf()
        )
    val typingActivityList: StateFlow<MutableList<Map<String, Any>>>
        get() = _typingActivityList

    private val _isConnectionError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConnectionError: StateFlow<Boolean>
        get() = _isConnectionError

    private val _isNotEnoughData: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNotEnoughData: StateFlow<Boolean>
        get() = _isNotEnoughData


    fun retrieveTypingActivity() {
        scope.launch {
            // Clear list before fetching
            _typingActivityList.value = mutableListOf()

            val request = Request.Builder()
                .url("$BASE_URL/recent-text-activity")
                .build()

            try {
                // Execute request
                val response = client.newCall(request).execute()

                _isConnectionError.value = false

                // Simulate a delay for loading animation
                delay(500)

                val responseBodyString = response.body?.string()
                responseBodyString?.let {
                    val jsonObj = JSONObject(responseBodyString)
                    val value = jsonObj.getString("recent_text_activity")

                    // When there's not enough data
                    if (value == "None") {
                        _isNotEnoughData.value = true
                        return@launch
                    }
                    _isNotEnoughData.value = false

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
                            "text" to text,
                            "predictions" to mapOf(
                                "Happy" to joy.toString(),
                                "Surprised" to surprise.toString(),
                                "Neutral" to neutral.toString(),
                                "Sad" to sadness.toString(),
                                "Angry" to anger.toString(),
                                "Disgusted" to disgust.toString(),
                                "Scared" to fear.toString(),
                                "Non-suicidal" to nonSuicidal.toString(),
                                "Suicidal" to suicidal.toString(),
                                "Primary" to primary,
                                "SuicideRisk" to capitalize(suicideRisk)
                            )
                        )

                        _typingActivityList.value =
                            _typingActivityList.value.toMutableList().apply {
                                add(emotionsMap)
                            }
                    }
                }
            } catch (_: Exception) {
                _isConnectionError.value = true
            }
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