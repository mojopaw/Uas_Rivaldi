package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.uas_rivaldi.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    //    Pendeklarasian variabel viewBinding dan Firebase
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      Menghubungkan Firebase
        firebaseAuth = FirebaseAuth.getInstance()

//      Tombol menuju halaman reset password
        binding.lupa.setOnClickListener {
            val intent = Intent(this, Forget::class.java)
            startActivity(intent)
        }

//      Tombol menuju halaman register
        binding.register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

//      Tombol untuk masuk atau login
        binding.btnLogin.setOnClickListener {

//            membuat variabel pada editText
            val email : String = binding.lEmail.text.toString().trim()
            val pass  : String = binding.lPass.text.toString().trim()

//            Kondisi mengecek EditText atau kolom email apabila kosong
            if (email.isEmpty()){
//               pesan yang akan tampil apabila kondisi terpenuhi
                binding.lEmail.error = "Email Tidak Boleh Kosong"
//                cursor akan berfocus pada kolom email
                binding.lEmail.requestFocus()
                return@setOnClickListener

//           Kondisi kedua apabila email tidak diisi dengan @ makan email tida valid
            }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                Pesan yang akan muncul apabila kondisi terpenuhi
                binding.lEmail.error = "Email Tidak Valid"
//                cursor akan berfocus pada kolom email
                binding.lEmail.requestFocus()
                return@setOnClickListener

//           Kondisi ketiga apa bila kolom password kosong atau kurang dari 8 karakter
            }else if(pass.isEmpty() || pass.length < 8){
//                Pesan yang akan muncul apabila kondisi terpenuhi
                binding.lPass.error = "Maksimal 8 karakter dan Tidak boleh kosong"
//                cursor akan berfocus pada kolom email
                binding.lPass.requestFocus()
                return@setOnClickListener

            }else{
//              Kondisi ini akan terpenuhi apabila kondisi diatas tidak terpenuhi atau tidak mengalami masalah
                loginUser(email,pass)
            }

        }

    }

    //  Function untuk melakukan Login dan melakukan pengecekan pada database firebase
    private fun loginUser(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
//            Kondisi apabila terpenuhi
            if(it.isSuccessful){
//               Apabila berhasil akan menampilkan pesan dibawah dan berpindah halamn ke halaman Home
                Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_SHORT).show()
                Intent(this, Home::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }else{
//                pesan yang akan muncul apabila kondisi diatas tidak terpenuhi akan menampilkan pesan error
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}