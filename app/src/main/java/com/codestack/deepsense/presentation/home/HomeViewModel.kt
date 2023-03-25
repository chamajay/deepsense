package com.codestack.deepsense.presentation.home

import androidx.lifecycle.ViewModel
import com.codestack.deepsense.R
import com.codestack.deepsense.core.Constants.BASE_URL
import com.codestack.deepsense.core.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class HomeViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _mood: MutableStateFlow<String> = MutableStateFlow("")
    private val _moodImage: MutableStateFlow<Int> = MutableStateFlow(R.drawable.ic_home_outlined)
    private val _emotionsPercentages: MutableStateFlow<MutableMap<String, Double>> = MutableStateFlow(
        mutableMapOf()
    )

    val mood: StateFlow<String>
        get() = _mood
    val moodImage: StateFlow<Int>
        get() = _moodImage
    val emotionsPercentages: StateFlow<MutableMap<String, Double>>
        get() = _emotionsPercentages

    fun retrieveMood() {
        scope.launch {
            // Reset mood before fetching
            _mood.value = ""

            val request = Request.Builder()
                .url("$BASE_URL/today-mood")
                .build()

            // Execute request
            val response = client.newCall(request).execute()

            // Simulate a delay for loading animation
            delay(500)

            val responseBodyString = response.body?.string()
            responseBodyString?.let {
                val jsonObj = JSONObject(responseBodyString)
                val emotionFromServer = jsonObj.getString("today_mood")

                val (mappedEmotion, imageResId) = Utils.mapEmotion(emotionFromServer)
                _mood.value = mappedEmotion
                _moodImage.value = imageResId
            }
        }
    }

    fun retrieveMoodPercentages() {
        scope.launch {
            // Reset before fetching
            _emotionsPercentages.value = mutableMapOf()

            val request = Request.Builder()
                .url("$BASE_URL/today_mood_percentages")
                .build()

            // Execute request
            val response = client.newCall(request).execute()

            // Simulate a delay for loading animation
            delay(700)

            val responseBodyString = response.body?.string()
            responseBodyString?.let {
                val jsonObj = JSONObject(responseBodyString)
                val jsonArr = JSONArray(jsonObj.getString("main_mood_percentages"))
                for (i in 0 until jsonArr.length()) {
                    val jsonObject = jsonArr.getJSONObject(i)
                    val emotion = jsonObject.getString("emotion")
                    val percentage = jsonObject.getDouble("percentage")
                    _emotionsPercentages.value = _emotionsPercentages.value.toMutableMap().apply {
                        put(emotion, percentage)
                    }
                }
            }
        }
    }
}