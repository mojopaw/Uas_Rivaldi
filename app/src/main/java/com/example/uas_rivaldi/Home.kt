package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_home)

        val masuk = findViewById<ImageView>(R.id.start)
        masuk.setOnClickListener {
            val intent = Intent(this,Content::class.java)
            startActivity(intent)
        }
    }
}