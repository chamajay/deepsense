package com.codestack.deepsense


import android.accessibilityservice.AccessibilityService
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.codestack.deepsense.core.Constants.BASE_URL
import com.codestack.deepsense.core.Utils
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.ConnectException
import java.util.*

class TextCaptureService : AccessibilityService() {

    private val client = OkHttpClient()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val chatApps = listOf(
        "com.whatsapp",
        "org.telegram.messenger",
        "org.thoughtcrime.securesms",
        "com.discord"
    )

    private val browsers = listOf(
        "com.android.chrome",
        "org.bromite.bromite",
        "org.mozilla.firefox"
    )

    private var prevTxt = ""
    private var currentHintText = ""


    override fun onServiceConnected() {
        Toast.makeText(applicationContext, "DeepSense: service started!", Toast.LENGTH_SHORT).show()
        sharedPrefUpdate(true)
    }


    override fun onInterrupt() {
        sharedPrefUpdate(false)
    }


    override fun onDestroy() {
        sharedPrefUpdate(false)
        Toast.makeText(applicationContext, "DeepSense: service stopped!", Toast.LENGTH_SHORT).show()
        job.cancel()
    }

    override fun onAccessibilityEvent(myevent: AccessibilityEvent?) {
        if (myevent != null) {
//            val eventType = myevent.eventType.toString()
            val packageName = myevent.packageName?.toString()
//            Log.i(TAG, "package: $packageName, event: $eventType")


            // browser flow
            if (packageName in browsers) {
                if (myevent.eventType == AccessibilityEvent.CONTENT_CHANGE_TYPE_PANE_TITLE) {
                    captureText()
                }

                if (myevent.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
                    val source = myevent.source ?: return
                    val currentTxt = source.text.toString()

                    processTxt(currentTxt)
                }
            }


            // chat apps flow
            if (packageName in chatApps) {
                // focus on a edtitext
                // 8
                if (myevent.eventType == AccessibilityEvent.TYPE_VIEW_FOCUSED) {
                    val source = myevent.source ?: return
                    val packageName = myevent.packageName

//                    Log.i(TAG, "view focused in ${packageName}")

                    if (source.text != null) {
                        if (packageName in listOf("com.whatsapp", "org.telegram.messenger")) {
                            currentHintText = "Message"
                        } else if (packageName == "org.thoughtcrime.securesms") {
                            currentHintText = "Signal message"
                        } else {
                            currentHintText = source.text.toString()
                        }
                    }
                }


                // when everything removed
                // 8192
                if (myevent.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED) {
                    val source = myevent.source ?: return
                    val currentTxt = source.text.toString()

//                    Log.i(TAG, "currentText: $currentTxt, currentHint: $currentHintText")

                    if (currentTxt != currentHintText) {
                        processTxt(currentTxt)
                    } else {
                        captureText()
                    }

                }
            }
        }
    }


    private fun processTxt(currentTxt: String) {
        if (prevTxt == "") {
            prevTxt = currentTxt
        } else {
            if (currentTxt.startsWith(prevTxt) || prevTxt.startsWith(currentTxt)) {
                prevTxt = currentTxt
            } else {
                prevTxt = ""
            }
        }
    }


    private fun captureText() {
        // only capture sentences with words 3 or more
        if (prevTxt.split(" ").size > 2) {
            Toast.makeText(applicationContext, prevTxt, Toast.LENGTH_SHORT).show()
            scope.launch {
                postTxt(prevTxt)
            }
        }
    }


    private suspend fun postTxt(capturedTxt: String) {
        withContext(Dispatchers.IO) {
            // make JSON object
            val jsonString = "{\"text\": \"$capturedTxt\"}"

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonString.toRequestBody(mediaType)

            val request = Request.Builder()
                .url("$BASE_URL/text-input")
                .post(body)
                .build()

            try {
                // execute request
                val response = client.newCall(request).execute()

                val responseBodyString = response.body?.string()
                responseBodyString?.let {
                    val jsonObj = JSONObject(responseBodyString)
                    val value = jsonObj.getString("suicidal_count")

                    if (value.toInt() >= 5) {
                        // send the sms
                        if (ContextCompat.checkSelfPermission(
                                applicationContext,
                                android.Manifest.permission.SEND_SMS
                            )
                            == PackageManager.PERMISSION_GRANTED
                        ) {
                            if (is24HoursPassedSinceLastSms()) {
                                val smsManager = SmsManager.getDefault()
                                val user =
                                    Utils.sharedPrefGetValue(applicationContext, "currentUser")
                                val phoneNumber = "+94781063592"
                                val message =
                                    "Hi, this is an automated message from DeepSense. Your friend $user may be at risk of suicide. Please check on them right now!"
                                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                                storeSmsSentDateInPrefs()
                            }
                        }
                    }
                }

            } catch (_: ConnectException) {
            }

            prevTxt = ""
        }
    }

    private fun sharedPrefUpdate(enabled: Boolean) {
        Utils.sharedPrefPutValue(applicationContext, "isAccessibilityServiceEnabled", enabled)
    }

    private fun storeSmsSentDateInPrefs() {
        val calendar = Calendar.getInstance()
        val currentDateInMillis = calendar.timeInMillis
        Utils.sharedPrefPutValue(applicationContext, "lastSmsSentDateInMillis", currentDateInMillis)
    }

    private fun is24HoursPassedSinceLastSms(): Boolean {
        val lastDateInMillis =
            Utils.sharedPrefGetValue(applicationContext, "lastSmsSentDateInMillis") as? Long
                ?: return true
        val calendar = Calendar.getInstance()

        calendar.timeInMillis = lastDateInMillis
        calendar.add(Calendar.DAY_OF_YEAR, 1)

        val nextDayInMillis = calendar.timeInMillis
        val currentDateInMillis = System.currentTimeMillis()

        return currentDateInMillis >= nextDayInMillis
    }

}