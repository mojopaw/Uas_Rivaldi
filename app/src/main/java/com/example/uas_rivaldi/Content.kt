package com.example.uas_rivaldi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class Content : AppCompatActivity() {
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENTS"
    }

    private var gridLayoutManager : GridLayoutManager? = null
    private var wisataList = mutableListOf<DataWisataBali>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_content)

        wisataList = ArrayList()

        val back = findViewById<TextView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }


//        Tombol untuk Signout
        val keluar = findViewById<TextView>(R.id.logout)
        keluar.setOnClickListener {
            logout()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerBali)
        gridLayoutManager = GridLayoutManager(applicationContext, 2,
            LinearLayoutManager.VERTICAL,false)

        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView.adapter = WisataBaliAdapter(this, wisataList){
            val intent = Intent(this, Detail::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }

        prepareHewanListData()

    }

    private fun prepareHewanListData(){
        var bali = DataWisataBali(
            R.drawable.g1,
            "Tari Kecak",
            "Tari kecak pertama kali dipentaskan di beberapa desa saja salah satunya adalah Desa Bona, Gianyar. Namun berkembang ke seluruh daerah di Bali dan selalu dihadirkan saat kegiatan-kegiatan seperti festival yang dilaksanakan oleh pemerintah maupun swasta.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g2,
            "Nusa Penida",
            "Nusa Penida adalah sebuah pulau (=nusa) bagian dari negara Republik Indonesia yang terletak di sebelah tenggara Bali yang dipisahkan oleh Selat Badung. Di dekat pulau ini terdapat juga pulau-pulau kecil lainnya yaitu Nusa Ceningan dan Nusa Lembongan.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g3,
            "Pura Ulun Danu",
            "Daya tarik utama Pura Ulun Danu Beratan sebagai tempat wisata terletak pada lokasi pura Ulun Danu. Pura Ulun Danu lokasinya ada di pinggir danau Beratan. Namun sebagian pura berada di tengah danau Beratan.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g4,
            "Ubud - Kintamani",
            "GOA gajah di Ubud, konon Goa Gajah merupakan tempat pertapaan orang suci zaman dahulu. Tempatnya sangat unik dan didalam goa terdapat Patung ganesha, mulut goa dihiasi dengan ornament Gajah.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g5,
            "Sawah Terasering Tegalalang",
            "Bali juga terkenal memiliki banyak objek wisata sawah terasering. Ada dua objek wisata sawah terasering yang terkenal di Bali. Yaitu, sawah terasering Jatiluwih, serta sawah terasering Tegalalang.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g6,
            "Bali Safari Marine Park",
            "Jika Anda liburan keluarga ke pulau Bali dengan anak, maka tempat wisata Bali Safari & Marine Park wajib Anda kunjungi. Bali Safari & Marine Park adalah sebuah kebun binatang yang memiliki luas area sangat luas, sekitar 400,000 meter persegi.",
        )
        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g7,
            "Pura Tanah Lot Tabanan Bali",
            "Daya tarik utama dari objek wisata Tanah Lot Bali terletak dari keunikan pura Tanah Lot yang berada di atas batu karang besar. Pada saat air laut pasang, askes jalan menuju pura Tanah Lot dari pantai akan di genangi air laut. Maka itu, pada saat air pasang, pura Tanah Lot terlihat seperti berada di tengah laut.",
        )

        wisataList.add(bali)

        bali = DataWisataBali(
            R.drawable.g8,
            "Garuda Wisnu Kencana",
            "Objek wisata Garuda Wisnu Kencana lebih terkenal dengan nama tempat wisata GWK Bali. Daya tarik dari GWK Bali terletak pada patung Garuda Wisnu Kencana. Selain dapat melihat patung dalam ukuran besar, di areal GWK Bali juga tersedia banyak fasilitas hiburan",
        )
        wisataList.add(bali)




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