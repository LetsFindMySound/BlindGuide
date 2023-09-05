package com.soundexpedition.blindguide

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_response)
        val response = intent.getStringExtra("response")

        val responseTextView = findViewById<TextView>(R.id.response_view_id)
        responseTextView.text = response
    }
}