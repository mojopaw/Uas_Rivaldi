package com.example.uas_rivaldi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataWisataBali(
    val img : Int,
    val title : String,
    val des : String,
):Parcelable
