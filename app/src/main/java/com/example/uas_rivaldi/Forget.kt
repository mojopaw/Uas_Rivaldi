package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.uas_rivaldi.databinding.ActivityForgetBinding
import com.google.firebase.auth.FirebaseAuth

class Forget : AppCompatActivity() {
    private lateinit var binding : ActivityForgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      tombol untuk kembali ke halalaman Login
        binding.cancel.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

//      Tombol reset password dari Email yang terdaftar
        binding.btnReset.setOnClickListener {
//          Pendeklarasian variabel email
            val email : String = binding.resetEmail.text.toString().trim()

//            Kondisi Mengecek kolom Email apabila kosong
            if(email.isEmpty()){
                binding.resetEmail.error = "Email Tidak Boleh Kosong"
                binding.resetEmail.requestFocus()
                return@setOnClickListener

//          Kondisi Mengecek kolom Email apabila tidak menginputkan @
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.resetEmail.error = "Email Tidak Valid"
                binding.resetEmail.requestFocus()
                return@setOnClickListener

            } else{
//                Kondisi dimana Koondisi diatas tidak terpenuhi maka kondisi dibawa yang di eksekusi
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
//                        Kondisi dimana sukses makan menampilkan pesan dibawah ini dan berpindah halaman ke Halaman Login
                        Toast.makeText(this, "Cek email untuk Reset Password", Toast.LENGTH_SHORT).show()
                        Intent(this,Login::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                        }

                    }else{
//                        Pesan apabila kondisi diatas tidak terpenuhi
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}