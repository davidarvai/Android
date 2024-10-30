package com.example.android_project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Az XML layout neve legyen splash_activity.xml

        // Gomb referencia
        val getStartedButton: Button = findViewById(R.id.getStartedButton)

        // Gomb kattintás kezelése
        getStartedButton.setOnClickListener {
            // Indítsa el a MainActivity-t
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Az Activity befejezése, hogy ne lehessen visszalépni
        }
    }
}