package com.example.areeba
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            // Create an intent to open the new activity
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)


        }
    }
}