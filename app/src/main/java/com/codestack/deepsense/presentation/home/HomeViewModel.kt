package com.codestack.deepsense.presentation.home

import androidx.lifecycle.ViewModel
import com.codestack.deepsense.core.Constants.BASE_URL
import com.codestack.deepsense.core.Utils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class HomeViewModel : ViewModel() {
    private val client = OkHttpClient()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val _mood: MutableStateFlow<String> = MutableStateFlow("")
    private val _moodImage: MutableStateFlow<Int> =
        MutableStateFlow(0) // 0 is a default image resource ID
    val mood: StateFlow<String>
        get() = _mood
    val moodImage: StateFlow<Int>
        get() = _moodImage

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
}