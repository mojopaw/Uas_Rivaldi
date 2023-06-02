package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.uas_rivaldi.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol untuk kembali ke halaman login
        binding.regidter.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

//      Menghubungkan Firebase
        firebaseAuth = FirebaseAuth.getInstance()

//      Tombol untuk
        binding.btnRegister.setOnClickListener{

//         Pendeklarasian variabel untuk menampung  EditText Email,Password,dan confirmasi password
            val email : String = binding.remail.text.toString().trim()
            val password : String = binding.rpass.text.toString().trim()
            val confirm : String = binding.confirm.text.toString().trim()

//          Kondisi mengecek kolom email apabila kosong
            if(email.isEmpty()){
//              pesan yang akan tampil apabila kondisi terpenuhi
                binding.remail.error = "Email Tidak Boleh Kosong"
//              cursor akan berfocus pada kolom email
                binding.remail.requestFocus()
                return@setOnClickListener

//          Kondisi kedua apabila email tidak diisi dengan @ makan email tida valid
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//              pesan yang akan tampil apabila kondisi terpenuhi
                binding.remail.error = "Email tidak valid"
//              cursor akan berfocus pada kolom email
                binding.remail.requestFocus()
                return@setOnClickListener

//           Kondisi ketiga apa bila kolom password kosong atau kurang dari 8 karakter
            } else if(password.isEmpty() || password.length < 8) {
//              pesan yang akan tampil apabila kondisi terpenuhi
                binding.rpass.error = "Minimal 8 karakter dan Tidak boleh kosong"
//              cursor akan berfocus pada kolom email
                binding.rpass.requestFocus()
                return@setOnClickListener

//           Kondisi keempat apa bila kolom password dan kolom confirm paswword berbeda
            } else if(password != confirm){
//              pesan yang akan tampil apabila kondisi terpenuhi
                binding.confirm.error = "Password tidak Sama"
//              cursor akan berfocus pada kolom password
                binding.confirm.requestFocus()
                return@setOnClickListener

            }else {
//              Kondisi ini akan terpenuhi apabila kondisi diatas tidak terpenuhi atau tidak mengalami masalah
                registerUser(email, password)
            }

        }
    }

    //  Function untuk menyimpan data yang diisikan pada kolom" pendaftaran diatas
    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
//            Kondisi apabila terpenuhi
            if(it.isSuccessful){
//              Akan memunculkan pesan dibawah ini dan berpindah halaman ke halaman Login
                Toast.makeText(this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
                Intent(this, Login::class.java).also{
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }else{
//             pesan yang akan muncul apabila kondisi diatas tidak terpenuhi akan menampilkan pesan error
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}