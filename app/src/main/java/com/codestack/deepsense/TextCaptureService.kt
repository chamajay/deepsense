package com.codestack.deepsense


import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.codestack.deepsense.core.Constants.TAG

class TextCaptureService : AccessibilityService() {

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
    }


    override fun onAccessibilityEvent(myevent: AccessibilityEvent?) {
        if (myevent != null) {
            val eventType = myevent.eventType.toString()
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
            prevTxt = ""
        }
    }


    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

}