package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)

        val detail = intent.getParcelableExtra<DataWisataBali>(Content.INTENT_PARCELABLE)

        val img = findViewById<ImageView>(R.id.detai_img)
        val name = findViewById<TextView>(R.id.detai_title)
        val dec = findViewById<TextView>(R.id.detail_des)

        img.setImageResource(detail!!.img)
        name.text = detail.title
        dec.text = detail.des

        val back = findViewById<TextView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Content::class.java)
            startActivity(intent)
        }

        val logout = findViewById<TextView>(R.id.logout)
        logout.setOnClickListener {
           logout()
        }

    }

    //    Function signOut dari akun dan berpindah halaman dengan memangggil function navigateToLoginPage
    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        navigateToLoginPage()
    }

    //    Function untuk berpindah halaman
    private fun navigateToLoginPage() {
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}